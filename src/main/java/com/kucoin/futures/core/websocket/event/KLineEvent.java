/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kucoin.futures.core.model.OrderBook;
import lombok.Data;

import java.util.List;

/**
 * @author blaze.tan
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KLineEvent {

    private String symbol;

    /**
     * index-0:Start time of the candle cycle
     * index-1:open price
     * index-2:close price
     * index-3:high price
     * index-4:low price
     * index-5:Transaction volume(This value is incorrect, please do not use it, we will fix it in subsequent versions)
     * index-6:Transaction amount
     */
    private List<String> candles;

    private Long time;
}
