/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.impl;

import com.kucoin.futures.core.constants.APIConstants;
import com.kucoin.futures.core.rest.interfaces.WebsocketAPI;
import com.kucoin.futures.core.websocket.ChooseServerStrategy;
import com.kucoin.futures.core.websocket.event.AccountChangeEvent;
import com.kucoin.futures.core.websocket.event.PositionChangeEvent;
import com.kucoin.futures.core.KucoinFuturesClientBuilder;
import com.kucoin.futures.core.KucoinFuturesPrivateWSClient;
import com.kucoin.futures.core.factory.HttpClientFactory;
import com.kucoin.futures.core.model.enums.PrivateChannelEnum;
import com.kucoin.futures.core.rest.adapter.WebsocketPrivateAPIAdaptor;
import com.kucoin.futures.core.websocket.KucoinFuturesAPICallback;
import com.kucoin.futures.core.websocket.event.KucoinEvent;
import com.kucoin.futures.core.websocket.event.StopOrderActivateEvent;
import com.kucoin.futures.core.websocket.event.StopOrderLifecycleEvent;
import com.kucoin.futures.core.websocket.listener.KucoinFuturesWebsocketListener;
import okhttp3.OkHttpClient;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class KucoinFuturesPrivateWSClientImpl extends KucoinFuturesPublicWSClientImpl implements KucoinFuturesPrivateWSClient {

    public KucoinFuturesPrivateWSClientImpl(KucoinFuturesClientBuilder kucoinClientBuilder) {
        this(HttpClientFactory.getPublicClient(), new KucoinFuturesWebsocketListener(), kucoinClientBuilder.getChooseServerStrategy(),
                new WebsocketPrivateAPIAdaptor(
                        kucoinClientBuilder.getBaseUrl(),
                        kucoinClientBuilder.getApiKey(),
                        kucoinClientBuilder.getSecret(),
                        kucoinClientBuilder.getPassPhrase()));
    }

    public KucoinFuturesPrivateWSClientImpl(OkHttpClient client,
                                     KucoinFuturesWebsocketListener listener,
                                     ChooseServerStrategy chooseServerStrategy,
                                     WebsocketAPI websocketAPI) {
        super(client, listener, chooseServerStrategy, websocketAPI);
    }

    @Override
    public String onStopOrderActivate(KucoinFuturesAPICallback<KucoinEvent<StopOrderActivateEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_ACTIVATE_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_ACTIVATE_TOPIC_PREFIX;
        return subscribe(topic, true, true);
    }

    @Override
    public String onStopOrderLifecycle(KucoinFuturesAPICallback<KucoinEvent<StopOrderLifecycleEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LIFECYCLE_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_LIFECYCLE_TOPIC_PREFIX;
        return subscribe(topic, true, true);
    }

    @Override
    public String onAccountBalance(KucoinFuturesAPICallback<KucoinEvent<AccountChangeEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_BALANCE_TOPIC_PREFIX, callback);
        }
        return subscribe(APIConstants.API_BALANCE_TOPIC_PREFIX, true, true);
    }

    @Override
    public String onPositionChange(KucoinFuturesAPICallback<KucoinEvent<PositionChangeEvent>> callback, String... symbols) {
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
