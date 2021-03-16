/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Data
public class ExecutionChangeEvent {

    private String sequence;

    private String symbol;

    private String side;

    private BigDecimal size;

    private BigDecimal price;

    private String takerOrderId;

    private long time;

    private String makerOrderId;

    private String tradeId;

}
