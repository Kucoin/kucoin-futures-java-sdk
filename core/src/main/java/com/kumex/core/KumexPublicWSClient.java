/*
 * Copyright 2019 Mek Global Limited
 */
package com.kumex.core;

import com.kumex.core.model.InstanceServer;
import com.kumex.core.model.enums.PublicChannelEnum;
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

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KumexPublicWSClient {

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
    String onTicker(KumexAPICallback<KucoinEvent<TickerChangeEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get Level 2 order book data.
     * The websocket system will send the incremental feed to you.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onLevel2Data(KumexAPICallback<KucoinEvent<Level2ChangeEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get Level 2 order book data.
     * The websocket system will send the incremental feed to you.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onLevel2Depth5Data(KumexAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get Level 2 order book data.
     * The websocket system will send the incremental feed to you.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onLevel2Depth50Data(KumexAPICallback<KucoinEvent<Level2OrderBookEvent>> callback, String... symbols);

    /**
     * For each order executed, the system will send you the match messages in the format as following.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onExecutionData(KumexAPICallback<KucoinEvent<ExecutionChangeEvent>> callback, String... symbols);

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
    String onLevel3Data(KumexAPICallback<KucoinEvent<Level3ChangeEvent>> callback, String... symbols);

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
    String onLevel3DataV2(KumexAPICallback<KucoinEvent<Level3ChangeEventV2>> callback, String... symbols);

    /**
     * Subscribe this topic to get the market data of the contract.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onContractMarketData(KumexAPICallback<KucoinEvent<ContractMarketEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get the system announcements.
     *
     * @param callback
     * @return
     */
    String onSystemAnnouncement(KumexAPICallback<KucoinEvent<AnnouncementEvent>> callback);

    /**
     * The transaction statistics will be pushed to users every 5 seconds.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onTransactionStatistic(KumexAPICallback<KucoinEvent<TransactionStatisticEvent>> callback, String... symbols);

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
