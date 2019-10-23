/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kumex.core.factory.KumexObjectMapper;
import com.kumex.core.model.InstanceServer;
import com.kumex.core.rest.interfaces.WebsocketAPI;
import com.kumex.core.rest.response.WebsocketTokenResponse;
import com.kumex.core.websocket.ChooseServerStrategy;
import com.kumex.core.websocket.event.KucoinEvent;
import com.kumex.core.websocket.listener.KumexWebsocketListener;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by chenshiwei on 2019/1/18.
 */
@Getter
public abstract class BaseWebsocketImpl implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseWebsocketImpl.class);

    private final ChooseServerStrategy chooseServerStrategy;
    private final OkHttpClient client;
    private final KumexWebsocketListener listener;
    private final WebsocketAPI websocketAPI;

    private WebSocket webSocket;

    protected BaseWebsocketImpl(OkHttpClient client, KumexWebsocketListener listener, ChooseServerStrategy chooseServerStrategy,
                                WebsocketAPI websocketAPI) {
        this.client = client;
        this.listener = listener;
        this.chooseServerStrategy = chooseServerStrategy;
        this.websocketAPI = websocketAPI;
    }

    public InstanceServer connect() throws IOException {
        Pair<WebSocket, InstanceServer> newWebSocket = createNewWebSocket();
        this.webSocket = newWebSocket.getLeft();
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
        client.dispatcher().executorService().shutdown();
    }

    private String serialize(Object o) {
        try {
            return KumexObjectMapper.INSTANCE.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failure serializing object", e);
        }
    }
}