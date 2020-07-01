/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionResponse {

    private String id;

    private String symbol;

    private Boolean autoDeposit;

    private BigDecimal maintMarginReq;

    private Integer riskLimit;

    private BigDecimal realLeverage;

    private Boolean crossMode;

    private double delevPercentage;

    private Long openingTimestamp;

    private long currentTimestamp;

    private Long currentQty;

    private BigDecimal currentCost;

    private BigDecimal currentComm;

    private BigDecimal unrealisedCost;

    private BigDecimal realisedGrossCost;

    private BigDecimal realisedCost;

    private Boolean isOpen;

    private BigDecimal markPrice;

    private BigDecimal markValue;

    private BigDecimal posCost;

    private BigDecimal posCross;

    private BigDecimal posInit;

    private BigDecimal posComm;

    private BigDecimal posLoss;

    private BigDecimal posMargin;

    private BigDecimal posMaint;

    private BigDecimal maintMargin;

    private BigDecimal realisedGrossPnl;

    private BigDecimal realisedPnl;

    private BigDecimal unrealisedPnl;

    private BigDecimal unrealisedPnlPcnt;

    private BigDecimal unrealisedRoePcnt;

    private BigDecimal avgEntryPrice;

    private BigDecimal liquidationPrice;

    private BigDecimal bankruptPrice;

    private String settleCurrency;

}
