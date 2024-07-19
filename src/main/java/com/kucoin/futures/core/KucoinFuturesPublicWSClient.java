/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core;

import com.kucoin.futures.core.model.InstanceServer;
import com.kucoin.futures.core.websocket.event.*;
import com.kucoin.futures.core.model.enums.PublicChannelEnum;
import com.kucoin.futures.core.websocket.KucoinFuturesAPICallback;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KucoinFuturesPublicWSClient {

    /**
     * Subscribe this topic to get the realtime push of BBO changes.
     * The ticker channel provides real-time price updates whenever a match happens.
     * If multiple orders are matched at the same time, only the last matching event will be pushed.
     * Please note that more information maybe added to messages from this channel in the near future.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    @Deprecated
    String onTicker(KucoinFuturesAPICallback<KucoinEvent<TickerChangeEvent>> callback, String... symbols);

    String onTickerV2(KucoinFuturesAPICallback<KucoinEvent<TickerV2ChangeEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get Level 2 order book data.
     * The websocket system will send the incremental feed to you.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onLevel2Data(KucoinFuturesAPICallback<KucoinEvent<Level2ChangeEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get Level 2 order book data.
     * The websocket system will send the incremental feed to you.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onLevel2Depth5Data(KucoinFuturesAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get Level 2 order book data.
     * The websocket system will send the incremental feed to you.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onLevel2Depth50Data(KucoinFuturesAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols);

    /**
     * Klines
     *
     * @param callback
     * @param subParam symbol_1min, 3min, 5min, 15min, 30min, 1hour, 2hour, 4hour, 8hour, 12hour, 1day, 1week, 1month
     * @return
     */
    String onKline(KucoinFuturesAPICallback<KucoinEvent<KLineEvent>> callback, String subParam);

    /**
     * For each order executed, the system will send you the match messages in the format as following.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onExecutionData(KucoinFuturesAPICallback<KucoinEvent<ExecutionChangeEvent>> callback, String... symbols);

    /**
     * Subsribe this topic to get the updated data for orders and trades.
     * This channel provides real-time updates on orders and trades.
     * These updates can be applied on to a Level 3 order book snapshot for users to
     * maintain an accurate and up-to-date copy of the exchange order book.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onLevel3DataV2(KucoinFuturesAPICallback<KucoinEvent<Level3ChangeEventV2>> callback, String... symbols);

    /**
     * Subscribe this topic to get the market data of the contract.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onContractMarketData(KucoinFuturesAPICallback<KucoinEvent<ContractMarketEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get the system announcements.
     *
     * @param callback
     * @return
     */
    String onSystemAnnouncement(KucoinFuturesAPICallback<KucoinEvent<AnnouncementEvent>> callback);

    /**
     * The transaction statistics will be pushed to users every 5 seconds.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onTransactionStatistic(KucoinFuturesAPICallback<KucoinEvent<TransactionStatisticEvent>> callback, String... symbols);

    /**
     * Unsubscribe from topics you have subscribed to.
     *
     * @param channelEnum
     * @param symbols
     * @return The unsubscribe request UUID, or null if sending failed.
     */
    String unsubscribe(PublicChannelEnum channelEnum, String... symbols);

    /**
     * To prevent the TCP link being disconnected by the server, the client side needs to send ping messages to the server to keep alive the link.
     * After the ping message is sent to the server, the system would return a pong message to the client side.
     *
     * @param requestId
     * @return The requestId back, or null if sending failed.
     */
    String ping(String requestId);

    /**
     * Connect to websocket server
     *
     * @return
     */
    InstanceServer connect() throws IOException;

    /**
     * Close client
     *
     */
    void close() throws IOException;
}
