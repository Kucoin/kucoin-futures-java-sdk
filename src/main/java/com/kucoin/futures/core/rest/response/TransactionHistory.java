/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @since 2019/7/26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionHistory {

    private long time;

    private String type;

    private BigDecimal amount;

    private BigDecimal fee;

    private BigDecimal accountEquity;

    private String status;

    private String remark;

    private long offset;

    private String currency;

}
