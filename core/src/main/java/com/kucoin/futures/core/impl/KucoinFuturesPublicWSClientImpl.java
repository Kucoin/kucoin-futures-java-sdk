/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.impl;

import com.kucoin.futures.core.KucoinFuturesClientBuilder;
import com.kucoin.futures.core.KucoinFuturesPublicWSClient;
import com.kucoin.futures.core.constants.APIConstants;
import com.kucoin.futures.core.factory.HttpClientFactory;
import com.kucoin.futures.core.model.enums.PublicChannelEnum;
import com.kucoin.futures.core.rest.adapter.WebsocketPublicAPIAdaptor;
import com.kucoin.futures.core.rest.interfaces.WebsocketAPI;
import com.kucoin.futures.core.websocket.ChooseServerStrategy;
import com.kucoin.futures.core.websocket.KucoinFuturesAPICallback;
import com.kucoin.futures.core.websocket.event.AnnouncementEvent;
import com.kucoin.futures.core.websocket.event.ContractMarketEvent;
import com.kucoin.futures.core.websocket.event.ExecutionChangeEvent;
import com.kucoin.futures.core.websocket.event.KucoinEvent;
import com.kucoin.futures.core.websocket.event.Level2ChangeEvent;
import com.kucoin.futures.core.websocket.event.Level2OrderBookEvent;
import com.kucoin.futures.core.websocket.event.Level3ChangeEvent;
import com.kucoin.futures.core.websocket.event.Level3ChangeEventV2;
import com.kucoin.futures.core.websocket.event.TickerChangeEvent;
import com.kucoin.futures.core.websocket.event.TransactionStatisticEvent;
import com.kucoin.futures.core.websocket.impl.BaseWebsocketImpl;
import com.kucoin.futures.core.websocket.listener.KucoinFuturesWebsocketListener;
import okhttp3.OkHttpClient;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chenshiwei on 2019/1/17.
 */
public class KucoinFuturesPublicWSClientImpl extends BaseWebsocketImpl implements KucoinFuturesPublicWSClient {

    public KucoinFuturesPublicWSClientImpl(KucoinFuturesClientBuilder kucoinClientBuilder) {
        this(HttpClientFactory.getPublicClient(), new KucoinFuturesWebsocketListener(),
                kucoinClientBuilder.getChooseServerStrategy(), new WebsocketPublicAPIAdaptor(kucoinClientBuilder.getBaseUrl()));
    }

    public KucoinFuturesPublicWSClientImpl(OkHttpClient client,
                                    KucoinFuturesWebsocketListener listener,
                                    ChooseServerStrategy chooseServerStrategy,
                                    WebsocketAPI websocketAPI) {
        super(client, listener, chooseServerStrategy, websocketAPI);
    }

    @Override
    public String onTicker(KucoinFuturesAPICallback<KucoinEvent<TickerChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_TICKER_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_TICKER_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Data(KucoinFuturesAPICallback<KucoinEvent<Level2ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL2_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Depth5Data(KucoinFuturesAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL2_DEPTH_5_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL2_DEPTH_5_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Depth50Data(KucoinFuturesAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL2_DEPTH_50_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL2_DEPTH_50_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onExecutionData(KucoinFuturesAPICallback<KucoinEvent<ExecutionChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_EXECUTION_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_EXECUTION_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel3Data(KucoinFuturesAPICallback<KucoinEvent<Level3ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL3_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL3_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel3DataV2(KucoinFuturesAPICallback<KucoinEvent<Level3ChangeEventV2>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL3_V2_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL3_V2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onContractMarketData(KucoinFuturesAPICallback<KucoinEvent<ContractMarketEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_CONTRACT_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_CONTRACT_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onSystemAnnouncement(KucoinFuturesAPICallback<KucoinEvent<AnnouncementEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_ANNOUNCEMENT_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_ANNOUNCEMENT_TOPIC_PREFIX;
        return subscribe(topic, false, true);
    }

    @Override
    public String onTransactionStatistic(KucoinFuturesAPICallback<KucoinEvent<TransactionStatisticEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_TRANSACTION_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_TRANSACTION_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String ping(String requestId) {
        return super.ping(requestId);
    }

    @Override
    public String unsubscribe(PublicChannelEnum channelEnum, String... symbols) {
        return super.unsubscribe(channelEnum.getTopicPrefix() + Arrays.stream(symbols).collect(Collectors.joining(",")),
                false, true);
    }

}
