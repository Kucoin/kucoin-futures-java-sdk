/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/1/18.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionHistoryResponse {

    private Long sequence;

    private BigDecimal price;

    private BigDecimal size;

    private String side;

    private String tradeId;

    private String takerOrderId;

    private String makerOrderId;

}
