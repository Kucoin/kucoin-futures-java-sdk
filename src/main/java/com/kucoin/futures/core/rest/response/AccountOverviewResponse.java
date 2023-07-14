/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountOverviewResponse {

    private String accountName;

    private BigDecimal accountEquity;

    private BigDecimal unrealisedPNL;

    private BigDecimal marginBalance;

    private BigDecimal positionMargin;

    private BigDecimal orderMargin;

    private BigDecimal frozenFunds;

    private BigDecimal availableBalance;

    private String currency;

}
