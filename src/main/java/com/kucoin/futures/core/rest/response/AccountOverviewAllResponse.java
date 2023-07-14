/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author blaze.tan
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountOverviewAllResponse {

    private AccountSummaryResponse summary;

    private List<AccountOverviewResponse> accounts;

}
