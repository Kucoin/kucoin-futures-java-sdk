/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kumex.core.model.OrderBook;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenshiwei on 2019/1/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookResponse extends OrderBook {

    private Long sequence;

    private String symbol;

}
