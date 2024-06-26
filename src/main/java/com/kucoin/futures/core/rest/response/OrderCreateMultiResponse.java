/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreateMultiResponse {
    private String orderId;
    private String clientOid;
    private String symbol;
    private String code;
    private String msg;
}
