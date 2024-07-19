package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class HistoryPositionResponse {

    private String closeId;

    private String positionId;

    private Long uid;

    private String userId;

    private String symbol;

    private String settleCurrency;

    private BigDecimal leverage;

    private String type;

    private String side;

    private BigDecimal closeSize;

    private BigDecimal pnl;

    private BigDecimal realisedGrossCost;

    private BigDecimal withdrawPnl;

    private BigDecimal roe;

    private BigDecimal tradeFee;

    private BigDecimal fundingFee;

    private Long openTime;

    private Long closeTime;

    private BigDecimal openPrice;

    private BigDecimal closePrice;

}
