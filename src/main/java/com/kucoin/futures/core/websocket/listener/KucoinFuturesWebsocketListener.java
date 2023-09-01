/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kucoin.futures.core.factory.KucoinFuturesObjectMapper;
import com.kucoin.futures.core.websocket.KucoinFuturesAPICallback;
import com.kucoin.futures.core.websocket.PrintCallback;
import com.kucoin.futures.core.websocket.event.KucoinEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KucoinFuturesWebsocketListener extends WebSocketListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KucoinFuturesWebsocketListener.class);

    private KucoinFuturesAPICallback<KucoinEvent> defaultCallback = new PrintCallback<>();

    private Map<String, KucoinFuturesAPICallback> callbackMap = new HashMap<>();

    private Map<String, TypeReference> typeReferenceMap = new HashMap<>();

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        LOGGER.debug("web socket open");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        LOGGER.debug("Got message: {}", text);
        JsonNode jsonObject = tree(text);
        LOGGER.debug("Parsed message OK");

        String type = jsonObject.get("type").asText();
        if (!type.equals("message")) {
            LOGGER.debug("Ignoring message type ({})", type);
            return;
        }

        String topic = jsonObject.get("topic").asText();

        Optional<String> first = callbackMap.keySet().stream().filter(topic::contains).findFirst();

        KucoinEvent kucoinEvent = (KucoinEvent) deserialize(text, typeReferenceMap.getOrDefault(first.get(), new TypeReference<KucoinEvent>() {}));

        if (first.isPresent()) {
            callbackMap.get(first.get()).onResponse(kucoinEvent);
        } else {
            defaultCallback.onResponse(kucoinEvent);
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        LOGGER.error("Error on private socket", t);
    }

    private JsonNode tree(String text) {
        try {
            return KucoinFuturesObjectMapper.INSTANCE.readTree(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }

    private <T> T deserialize(String text, TypeReference<T> typeReference) {
        try {
            return KucoinFuturesObjectMapper.INSTANCE.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }
}
