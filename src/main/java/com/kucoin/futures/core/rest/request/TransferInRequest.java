package com.kucoin.futures.core.rest.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName TransferInRequest.java
 * @Description
 * @createTime 2023/05/25日 10:31:00
 */
@Data
public class TransferInRequest {

    private BigDecimal amount;

    private String currency;

    private String payAccountType;
}
