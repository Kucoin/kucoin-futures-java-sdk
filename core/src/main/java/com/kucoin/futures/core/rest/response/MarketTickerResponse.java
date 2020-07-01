/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/2/22.
 */
@Data
public class MarketTickerResponse {

    private String symbol;

    private BigDecimal buy;

    private BigDecimal sell;

    private BigDecimal changeRate;

    private BigDecimal changePrice;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal vol;

    private BigDecimal last;

    private BigDecimal volValue;

}
