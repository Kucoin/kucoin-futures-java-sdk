/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blaze.tan
 */
@Data
@Builder
public class WithdrawMarginRequest {

    /**
     * Symbol of the contract
     */
    private String symbol;

    /**
     * The size of the position that can be deposited. If it is USDT-margin, it represents the amount of USDT.
     * If it is coin-margin, this value represents the number of coins
     */
    private BigDecimal withdrawAmount;

}
