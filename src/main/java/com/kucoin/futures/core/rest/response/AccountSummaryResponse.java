package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSummaryResponse {

    private BigDecimal accountEquityTotal;

    private BigDecimal unrealisedPNLTotal;

    private BigDecimal marginBalanceTotal;

    private BigDecimal positionMarginTotal;

    private BigDecimal orderMarginTotal;

    private BigDecimal frozenFundsTotal;

    private BigDecimal availableBalanceTotal;

    private String currency;

}
