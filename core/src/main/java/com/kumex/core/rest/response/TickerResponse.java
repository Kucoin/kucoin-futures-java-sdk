/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerResponse {

    private String sequence;

    private String symbol;

    private String side;

    private BigDecimal size;

    private BigDecimal price;

    private BigDecimal bestAskPrice;

    private BigDecimal bestBidPrice;

    private BigDecimal bestAskSize;

    private BigDecimal bestBidSize;

    private String tradeId;

    private Long ts;

}
