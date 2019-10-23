/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Active order value calculation response
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/8/14
 */
@Data
public class ActiveOrderResponse {

    private int openOrderBuySize;

    private int openOrderSellSize;

    private BigDecimal openOrderBuyCost;

    private BigDecimal openOrderSellCost;

}
