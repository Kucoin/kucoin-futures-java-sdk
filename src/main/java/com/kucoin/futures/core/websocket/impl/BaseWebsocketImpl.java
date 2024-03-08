/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kucoin.futures.core.factory.KucoinFuturesObjectMapper;
import com.kucoin.futures.core.model.InstanceServer;
import com.kucoin.futures.core.rest.interfaces.WebsocketAPI;
import com.kucoin.futures.core.rest.response.WebsocketTokenResponse;
import com.kucoin.futures.core.websocket.ChooseServerStrategy;
import com.kucoin.futures.core.websocket.event.KucoinEvent;
import com.kucoin.futures.core.websocket.listener.KucoinFuturesWebsocketListener;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by chenshiwei on 2019/1/18.
 */
@Getter
public abstract class BaseWebsocketImpl implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseWebsocketImpl.class);

    private final ChooseServerStrategy chooseServerStrategy;
    private final OkHttpClient client;
    private final KucoinFuturesWebsocketListener listener;
    private final WebsocketAPI websocketAPI;

    private WebSocket webSocket;

    private final Timer pingTimer = new Timer("FUTURES-WS-PING-TIMER");

    protected BaseWebsocketImpl(OkHttpClient client, KucoinFuturesWebsocketListener listener, ChooseServerStrategy chooseServerStrategy,
                                WebsocketAPI websocketAPI) {
        this.client = client;
        this.listener = listener;
        this.chooseServerStrategy = chooseServerStrategy;
        this.websocketAPI = websocketAPI;
    }

    public InstanceServer connect() throws IOException {
        Pair<WebSocket, InstanceServer> newWebSocket = createNewWebSocket();
        this.webSocket = newWebSocket.getLeft();
        pingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ping(UUID.randomUUID().toString());
            }
        },0, newWebSocket.getRight().getPingInterval());
        return newWebSocket.getRight();
    }

    protected WebsocketTokenResponse requestToken() throws IOException {
        return this.websocketAPI.getToken();
    }

    private Pair<WebSocket, InstanceServer> createNewWebSocket() throws IOException {
        WebsocketTokenResponse websocketToken = requestToken();
        InstanceServer instanceServer = chooseServerStrategy.choose(websocketToken.getInstanceServers());
        String streamingUrl = String.format("%s", instanceServer.getEndpoint()
                + "?token=" + websocketToken.getToken() + "&acceptUserMessage=true");
        Request request = new Request.Builder().url(streamingUrl).build();
        return Pair.of(client.newWebSocket(request, listener), instanceServer);
    }

    protected String ping(String requestId) {
        KucoinEvent<Void> ping = new KucoinEvent<>();
        ping.setId(requestId);
        ping.setType("ping");
        if (webSocket.send(serialize(ping))) {
            return requestId;
        }
        return null;
    }

    protected String subscribe(String topic, boolean privateChannel, boolean response) {
        String uuid = UUID.randomUUID().toString();
        KucoinEvent<Void> subscribe = new KucoinEvent<>();
        subscribe.setId(uuid);
        subscribe.setType("subscribe");
        subscribe.setTopic(topic);
        subscribe.setPrivateChannel(privateChannel);
        subscribe.setResponse(response);
        if (webSocket.send(serialize(subscribe))) {
            return uuid;
        }
        return null;
    }

    protected String unsubscribe(String topic, boolean privateChannel, boolean response) {
        String uuid = UUID.randomUUID().toString();
        KucoinEvent<Void> subscribe = new KucoinEvent<>();
        subscribe.setId(uuid);
        subscribe.setType("unsubscribe");
        subscribe.setTopic(topic);
        subscribe.setPrivateChannel(privateChannel);
        subscribe.setResponse(response);
        if (webSocket.send(serialize(subscribe))) {
            return uuid;
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        LOGGER.debug("Web Socket Close");
        if (webSocket != null) {
            webSocket.close(1000, "Normal closure");
        }
        pingTimer.cancel();
        client.dispatcher().executorService().shutdown();
    }

    private String serialize(Object o) {
        try {
            return KucoinFuturesObjectMapper.INSTANCE.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failure serializing object", e);
        }
    }
}