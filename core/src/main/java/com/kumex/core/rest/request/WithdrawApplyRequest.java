/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Created by zicong.lu on 2018/12/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Builder
public class WithdrawApplyRequest {

    /**
     * Currency
     */
    private String currency;

    /**
     * Withdrawal address
     */
    private String address;

    /**
     * Withdrawal amount, a multiple and positive number of the amount precision (fees excluded)
     */
    private BigDecimal amount;

    /**
     * [optional] Internal withdrawal or not. Default setup: false
     */
    @Builder.Default
    private Boolean isInner = false;

    /**
     * [optional] Remark
     */
    private String remark;
}
