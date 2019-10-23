/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kumex.core.factory.KumexObjectMapper;
import com.kumex.core.websocket.KumexAPICallback;
import com.kumex.core.websocket.PrintCallback;
import com.kumex.core.websocket.event.KucoinEvent;
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
public class KumexWebsocketListener extends WebSocketListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KumexWebsocketListener.class);

    private KumexAPICallback<KucoinEvent> defaultCallback = new PrintCallback<>();

    private Map<String, KumexAPICallback> callbackMap = new HashMap<>();

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

        KucoinEvent kucoinEvent = deserialize(text, new TypeReference<KucoinEvent>() {});
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
            return KumexObjectMapper.INSTANCE.readTree(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }

    private <T> T deserialize(String text, TypeReference<T> typeReference) {
        try {
            return KumexObjectMapper.INSTANCE.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }
}
