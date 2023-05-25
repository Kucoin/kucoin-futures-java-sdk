package com.kucoin.futures.core.rest.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName TransferOutRequest.java
 * @Description
 * @createTime 2023/05/25日 10:30:00
 */
@Data
public class TransferOutRequest {

    private BigDecimal amount;

    private String currency;

    private String recAccountType;
}
