/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author blaze.tan
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TickerV2ChangeEvent {

    private String symbol;

    private BigDecimal bestBidSize;

    private BigDecimal bestBidPrice;

    private BigDecimal bestAskSize;

    private BigDecimal bestAskPrice;

    private Long ts;

}
