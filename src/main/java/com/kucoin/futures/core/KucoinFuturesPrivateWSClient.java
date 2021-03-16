/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core;

import com.kucoin.futures.core.websocket.KucoinFuturesAPICallback;
import com.kucoin.futures.core.websocket.event.AccountChangeEvent;
import com.kucoin.futures.core.websocket.event.KucoinEvent;
import com.kucoin.futures.core.websocket.event.PositionChangeEvent;
import com.kucoin.futures.core.model.enums.PrivateChannelEnum;
import com.kucoin.futures.core.websocket.event.StopOrderActivateEvent;
import com.kucoin.futures.core.websocket.event.StopOrderLifecycleEvent;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KucoinFuturesPrivateWSClient extends KucoinFuturesPublicWSClient {

    /**
     * When a stop-limit order is triggered, you would receive an activate message which means that this order started the matching life cycle.
     *
     * @param callback
     * @return The subscription UUID, or null if sending failed.
     */
    @Deprecated
    String onStopOrderActivate(KucoinFuturesAPICallback<KucoinEvent<StopOrderActivateEvent>> callback);

    /**
     *
     *
     * @param callback
     * @return
     */
    String onStopOrderLifecycle(KucoinFuturesAPICallback<KucoinEvent<StopOrderLifecycleEvent>> callback);

    /**
     * You will receive this message when an account balance changes. The message contains the details of the change.
     *
     * @param callback
     * @return The subscription UUID, or null if sending failed.
     */
    String onAccountBalance(KucoinFuturesAPICallback<KucoinEvent<AccountChangeEvent>> callback);

    /**
     * You will receive this message when the position changes.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onPositionChange(KucoinFuturesAPICallback<KucoinEvent<PositionChangeEvent>> callback, String... symbols);

    /**
     * Unsubscribe from topics you have subscribed to.
     *
     * @param channelEnum
     * @param symbols
     * @return The ID for the unsubscribe request, or null if sending failed.
     */
    String unsubscribe(PrivateChannelEnum channelEnum, String... symbols);

}
