/*
 * Copyright 2019 Mek Global Limited.
 */

package com.kumex.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/18
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionChangeEvent {

    private BigDecimal realisedGrossPnl;

    private Boolean crossMode;

    private BigDecimal liquidationPrice;

    private BigDecimal posLoss;

    private BigDecimal avgEntryPrice;

    private BigDecimal unrealisedPnl;

    private BigDecimal markPrice;

    private BigDecimal posMargin;

    private BigDecimal riskLimit;

    private BigDecimal unrealisedCost;

    private BigDecimal posComm;

    private BigDecimal posMaint;

    private BigDecimal posCost;

    private BigDecimal maintMarginReq;

    private BigDecimal bankruptPrice;

    private BigDecimal realisedCost;

    private BigDecimal markValue;

    private BigDecimal posInit;

    private BigDecimal realisedPnl;

    private BigDecimal maintMargin;

    private BigDecimal realLeverage;

    private BigDecimal currentCost;

    private Long openingTimestamp;

    private BigDecimal currentQty;

    private BigDecimal delevPercentage;

    private BigDecimal currentComm;

    private BigDecimal realisedGrossCost;

    private Boolean isOpen;

    private BigDecimal posCross;

    private Long currentTimestamp;

    private BigDecimal unrealisedRoePcnt;

    private BigDecimal unrealisedPnlPcnt;

    private Long fundingTime;

    private BigDecimal qty;

    private BigDecimal fundingRate;

    private BigDecimal fundingFee;

    private Long ts;

}
