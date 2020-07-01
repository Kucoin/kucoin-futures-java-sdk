/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawQuotaResponse {

    private String currency;

    private BigDecimal limitAmount;

    private BigDecimal usedAmount;

    private BigDecimal remainAmount;

    private BigDecimal availableAmount;

    private BigDecimal withdrawMinFee;

    private BigDecimal innerWithdrawMinFee;

    private BigDecimal withdrawMinSize;

    @JsonProperty("isWithdrawEnabled")
    private Boolean isWithdrawEnabled;

    private Integer precision;

}
