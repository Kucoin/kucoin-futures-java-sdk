package com.kucoin.futures.core.rest.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class TransferInRequest {

    private BigDecimal amount;

    private String currency;

    private String payAccountType;
}
