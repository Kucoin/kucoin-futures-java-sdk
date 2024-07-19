/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeFeeResponse {

    private String symbol;

    private BigDecimal takerFeeRate;

    private BigDecimal makerFeeRate;

}
