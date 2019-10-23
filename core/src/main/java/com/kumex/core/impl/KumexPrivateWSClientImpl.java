/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.impl;

import com.kumex.core.KumexClientBuilder;
import com.kumex.core.KumexPrivateWSClient;
import com.kumex.core.constants.APIConstants;
import com.kumex.core.factory.HttpClientFactory;
import com.kumex.core.model.enums.PrivateChannelEnum;
import com.kumex.core.rest.adapter.WebsocketPrivateAPIAdaptor;
import com.kumex.core.rest.interfaces.WebsocketAPI;
import com.kumex.core.websocket.ChooseServerStrategy;
import com.kumex.core.websocket.KumexAPICallback;
import com.kumex.core.websocket.event.AccountChangeEvent;
import com.kumex.core.websocket.event.KucoinEvent;
import com.kumex.core.websocket.event.PositionChangeEvent;
import com.kumex.core.websocket.event.StopOrderActivateEvent;
import com.kumex.core.websocket.listener.KumexWebsocketListener;
import okhttp3.OkHttpClient;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class KumexPrivateWSClientImpl extends KumexPublicWSClientImpl implements KumexPrivateWSClient {

    public KumexPrivateWSClientImpl(KumexClientBuilder kucoinClientBuilder) {
        this(HttpClientFactory.getPublicClient(), new KumexWebsocketListener(), kucoinClientBuilder.getChooseServerStrategy(),
                new WebsocketPrivateAPIAdaptor(
                        kucoinClientBuilder.getBaseUrl(),
                        kucoinClientBuilder.getApiKey(),
                        kucoinClientBuilder.getSecret(),
                        kucoinClientBuilder.getPassPhrase()));
    }

    public KumexPrivateWSClientImpl(OkHttpClient client,
                                     KumexWebsocketListener listener,
                                     ChooseServerStrategy chooseServerStrategy,
                                     WebsocketAPI websocketAPI) {
        super(client, listener, chooseServerStrategy, websocketAPI);
    }

    @Override
    public String onStopOrderActivate(KumexAPICallback<KucoinEvent<StopOrderActivateEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_ACTIVATE_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_ACTIVATE_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, true, true);
    }

    @Override
    public String onAccountBalance(KumexAPICallback<KucoinEvent<AccountChangeEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_BALANCE_TOPIC_PREFIX, callback);
        }
        return subscribe(APIConstants.API_BALANCE_TOPIC_PREFIX, true, true);
    }

    @Override
    public String onPositionChange(KumexAPICallback<KucoinEvent<PositionChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_POSITION_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_POSITION_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, true, true);
    }

    @Override
    public String unsubscribe(PrivateChannelEnum channelEnum, String... symbols) {
        return super.unsubscribe(channelEnum.getTopicPrefix() + Arrays.stream(symbols).collect(Collectors.joining(",")),
                true, true);
    }
}
