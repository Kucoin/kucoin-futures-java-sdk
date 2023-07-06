/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Active order value calculation response
 * @author chenshiwei
 * @since 2019/8/14
 */
@Data
public class ActiveOrderResponse {

    private int openOrderBuySize;

    private int openOrderSellSize;

    private BigDecimal openOrderBuyCost;

    private BigDecimal openOrderSellCost;

    private String settleCurrency;

}
