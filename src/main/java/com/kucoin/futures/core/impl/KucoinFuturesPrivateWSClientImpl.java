/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kucoin.futures.core.constants.APIConstants;
import com.kucoin.futures.core.rest.interfaces.WebsocketAPI;
import com.kucoin.futures.core.websocket.ChooseServerStrategy;
import com.kucoin.futures.core.websocket.event.*;
import com.kucoin.futures.core.KucoinFuturesClientBuilder;
import com.kucoin.futures.core.KucoinFuturesPrivateWSClient;
import com.kucoin.futures.core.factory.HttpClientFactory;
import com.kucoin.futures.core.model.enums.PrivateChannelEnum;
import com.kucoin.futures.core.rest.adapter.WebsocketPrivateAPIAdaptor;
import com.kucoin.futures.core.websocket.KucoinFuturesAPICallback;
import com.kucoin.futures.core.websocket.listener.KucoinFuturesWebsocketListener;
import okhttp3.OkHttpClient;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class KucoinFuturesPrivateWSClientImpl extends KucoinFuturesPublicWSClientImpl implements KucoinFuturesPrivateWSClient {

    public KucoinFuturesPrivateWSClientImpl(KucoinFuturesClientBuilder kucoinClientBuilder) {
        this(HttpClientFactory.getPublicClient(), new KucoinFuturesWebsocketListener(), kucoinClientBuilder.getChooseServerStrategy(),
                new WebsocketPrivateAPIAdaptor(
                        kucoinClientBuilder.getBaseUrl(),
                        kucoinClientBuilder.getApiKey()));
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
            this.getListener().getTypeReferenceMap().put(APIConstants.API_ACTIVATE_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<StopOrderActivateEvent>>() {});
        }
        String topic = APIConstants.API_ACTIVATE_TOPIC_PREFIX;
        return subscribe(topic, true, true);
    }

    @Override
    public String onStopOrderLifecycle(KucoinFuturesAPICallback<KucoinEvent<StopOrderLifecycleEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LIFECYCLE_TOPIC_PREFIX, callback);
            this.getListener().getTypeReferenceMap().put(APIConstants.API_LIFECYCLE_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<StopOrderLifecycleEvent>>() {});
        }
        String topic = APIConstants.API_LIFECYCLE_TOPIC_PREFIX;
        return subscribe(topic, true, true);
    }

    @Override
    public String onAccountBalance(KucoinFuturesAPICallback<KucoinEvent<AccountChangeEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_BALANCE_TOPIC_PREFIX, callback);
            this.getListener().getTypeReferenceMap().put(APIConstants.API_BALANCE_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<AccountChangeEvent>>() {});
        }
        return subscribe(APIConstants.API_BALANCE_TOPIC_PREFIX, true, true);
    }

    @Override
    public String onPositionChange(KucoinFuturesAPICallback<KucoinEvent<PositionChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_POSITION_TOPIC_PREFIX, callback);
            this.getListener().getTypeReferenceMap().put(APIConstants.API_POSITION_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<PositionChangeEvent>>() {});
        }
        String topic = APIConstants.API_POSITION_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, true, true);
    }

    @Override
    public String onPositionAllChange(KucoinFuturesAPICallback<KucoinEvent<PositionChangeEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_POSITION_ALL_TOPIC_PREFIX, callback);
            this.getListener().getTypeReferenceMap().put(APIConstants.API_POSITION_ALL_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<PositionChangeEvent>>() {});
        }
        String topic = APIConstants.API_POSITION_ALL_TOPIC_PREFIX;
        return subscribe(topic, true, true);
    }

    @Override
    public String onOrderChange(KucoinFuturesAPICallback<KucoinEvent<OrderChangeEvent>> callback, String symbol) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_SYMBOL_ORDER_CHANGE_TOPIC_PREFIX, callback);
            this.getListener().getTypeReferenceMap().put(APIConstants.API_SYMBOL_ORDER_CHANGE_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<OrderChangeEvent>>() {});
        }
        String topic = APIConstants.API_SYMBOL_ORDER_CHANGE_TOPIC_PREFIX + symbol;
        return subscribe(topic, true, true);
    }

    @Override
    public String onOrderChange(KucoinFuturesAPICallback<KucoinEvent<OrderChangeEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_ORDER_CHANGE_TOPIC_PREFIX, callback);
            this.getListener().getTypeReferenceMap().put(APIConstants.API_ORDER_CHANGE_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<OrderChangeEvent>>() {});
        }
        return subscribe(APIConstants.API_ORDER_CHANGE_TOPIC_PREFIX, true, true);
    }

    @Override
    public String unsubscribe(PrivateChannelEnum channelEnum, String... symbols) {
        return super.unsubscribe(channelEnum.getTopicPrefix() + String.join(",", symbols),
                true, true);
    }

}
