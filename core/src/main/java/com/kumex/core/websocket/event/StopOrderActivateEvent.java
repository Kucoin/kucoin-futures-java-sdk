/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket.event;

import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/23.
 */
@Data
public class StopOrderActivateEvent {

    private Boolean success;

    private String orderId;

    private String errorCode;

    private long ts;

}
