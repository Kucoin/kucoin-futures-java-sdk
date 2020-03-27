/*
 * Copyright 2019 Mek Global Limited
 */
package com.kumex.core;

import com.kumex.core.model.enums.PrivateChannelEnum;
import com.kumex.core.websocket.KumexAPICallback;
import com.kumex.core.websocket.event.AccountChangeEvent;
import com.kumex.core.websocket.event.KucoinEvent;
import com.kumex.core.websocket.event.PositionChangeEvent;
import com.kumex.core.websocket.event.StopOrderActivateEvent;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KumexPrivateWSClient extends KumexPublicWSClient {

    /**
     * When a stop-limit order is triggered, you would receive an activate message which means that this order started the matching life cycle.
     *
     * @param callback
     * @return The subscription UUID, or null if sending failed.
     */
    String onStopOrderActivate(KumexAPICallback<KucoinEvent<StopOrderActivateEvent>> callback);

    /**
     * You will receive this message when an account balance changes. The message contains the details of the change.
     *
     * @param callback
     * @return The subscription UUID, or null if sending failed.
     */
    String onAccountBalance(KumexAPICallback<KucoinEvent<AccountChangeEvent>> callback);

    /**
     * You will receive this message when the position changes.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onPositionChange(KumexAPICallback<KucoinEvent<PositionChangeEvent>> callback, String... symbols);

    /**
     * Unsubscribe from topics you have subscribed to.
     *
     * @param channelEnum
     * @param symbols
     * @return The ID for the unsubscribe request, or null if sending failed.
     */
    String unsubscribe(PrivateChannelEnum channelEnum, String... symbols);

}
