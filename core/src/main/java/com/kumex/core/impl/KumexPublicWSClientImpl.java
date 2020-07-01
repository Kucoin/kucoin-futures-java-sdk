/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.impl;

import com.kumex.core.KumexClientBuilder;
import com.kumex.core.KumexPublicWSClient;
import com.kumex.core.constants.APIConstants;
import com.kumex.core.factory.HttpClientFactory;
import com.kumex.core.model.enums.PublicChannelEnum;
import com.kumex.core.rest.adapter.WebsocketPublicAPIAdaptor;
import com.kumex.core.rest.interfaces.WebsocketAPI;
import com.kumex.core.websocket.ChooseServerStrategy;
import com.kumex.core.websocket.KumexAPICallback;
import com.kumex.core.websocket.event.AnnouncementEvent;
import com.kumex.core.websocket.event.ContractMarketEvent;
import com.kumex.core.websocket.event.ExecutionChangeEvent;
import com.kumex.core.websocket.event.KucoinEvent;
import com.kumex.core.websocket.event.Level2ChangeEvent;
import com.kumex.core.websocket.event.Level2OrderBookEvent;
import com.kumex.core.websocket.event.Level3ChangeEvent;
import com.kumex.core.websocket.event.Level3ChangeEventV2;
import com.kumex.core.websocket.event.TickerChangeEvent;
import com.kumex.core.websocket.event.TransactionStatisticEvent;
import com.kumex.core.websocket.impl.BaseWebsocketImpl;
import com.kumex.core.websocket.listener.KumexWebsocketListener;
import okhttp3.OkHttpClient;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chenshiwei on 2019/1/17.
 */
public class KumexPublicWSClientImpl extends BaseWebsocketImpl implements KumexPublicWSClient {

    public KumexPublicWSClientImpl(KumexClientBuilder kucoinClientBuilder) {
        this(HttpClientFactory.getPublicClient(), new KumexWebsocketListener(),
                kucoinClientBuilder.getChooseServerStrategy(), new WebsocketPublicAPIAdaptor(kucoinClientBuilder.getBaseUrl()));
    }

    public KumexPublicWSClientImpl(OkHttpClient client,
                                    KumexWebsocketListener listener,
                                    ChooseServerStrategy chooseServerStrategy,
                                    WebsocketAPI websocketAPI) {
        super(client, listener, chooseServerStrategy, websocketAPI);
    }

    @Override
    public String onTicker(KumexAPICallback<KucoinEvent<TickerChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_TICKER_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_TICKER_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Data(KumexAPICallback<KucoinEvent<Level2ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL2_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Depth5Data(KumexAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL2_DEPTH_5_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL2_DEPTH_5_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Depth50Data(KumexAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL2_DEPTH_50_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL2_DEPTH_50_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onExecutionData(KumexAPICallback<KucoinEvent<ExecutionChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_EXECUTION_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_EXECUTION_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel3Data(KumexAPICallback<KucoinEvent<Level3ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL3_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL3_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel3DataV2(KumexAPICallback<KucoinEvent<Level3ChangeEventV2>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_LEVEL3_V2_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_LEVEL3_V2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onContractMarketData(KumexAPICallback<KucoinEvent<ContractMarketEvent>> callback, String... symbols) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_CONTRACT_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_CONTRACT_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onSystemAnnouncement(KumexAPICallback<KucoinEvent<AnnouncementEvent>> callback) {
        if (callback != null) {
            this.getListener().getCallbackMap().put(APIConstants.API_ANNOUNCEMENT_TOPIC_PREFIX, callback);
        }
        String topic = APIConstants.API_ANNOUNCEMENT_TOPIC_PREFIX;
        return subscribe(topic, false, true);
    }

    @Override
    public String onTransactionStatistic(KumexAPICallback<KucoinEvent<TransactionStatisticEvent>> callback, String... symbols) {
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
