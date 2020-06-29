/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/7/25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountOverviewResponse {

    private BigDecimal accountEquity;

    private BigDecimal unrealisedPNL;

    private BigDecimal marginBalance;

    private BigDecimal positionMargin;

    private BigDecimal orderMargin;

    private BigDecimal frozenFunds;

    private BigDecimal availableBalance;

    private String currency;

}
