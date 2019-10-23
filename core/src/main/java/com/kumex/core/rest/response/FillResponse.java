/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FillResponse {

    private String symbol;

    private String tradeId;

    private String orderId;

    private String side;

    private String liquidity;

    private BigDecimal price;

    private BigDecimal size;

    private BigDecimal value;

    private BigDecimal feeRate;

    private BigDecimal fixFee;

    private String feeCurrency;

    private String stop;

    private BigDecimal fee;

    private String orderType;

    private String tradeType;

    private Date createdAt;

    public String getSide() {
        return this.side == null ? null : this.side.toLowerCase();
    }

    public String getLiquidity() {
        return this.liquidity == null ? null : this.liquidity.toLowerCase();
    }

}
