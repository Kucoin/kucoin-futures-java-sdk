/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @since 2019/10/15
 */
@Data
public class MarkPriceResponse {

    private String symbol;

    private Long granularity;

    private Long timePoint;

    private BigDecimal value;

    private BigDecimal indexPrice;

}
