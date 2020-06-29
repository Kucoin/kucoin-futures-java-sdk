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

    /**
     * [Optional] The chain name of currency, e.g. The available value for USDT are OMNI, ERC20, TRC20, default is ERC20.
     * This only apply for multi-chain currency, and there is no need for single chain currency.
     */
    private String chain;

    /**
     * [Optional] Address remark. If there’s no remark, it is empty.
     * When you withdraw from other platforms to the KuCoin Futures, you need to fill in memo(tag).
     * If you do not fill memo (tag), your deposit may not be available, please be cautious.
     */
    private String memo;
}
