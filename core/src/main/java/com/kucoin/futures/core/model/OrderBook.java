/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.model;

import lombok.Data;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Data
public class OrderBook {

    /**
     * [price, size] for level2,
     * [order placing nanosecond time, orderId, price, size, nanosecond time at which the order enters the order book] for level3
     */
    private List<List<String>> asks;

    /**
     * [price, size] for level2,
     * [order placing nanosecond time, orderId, price, size, nanosecond time at which the order enters the order book] for level3
     */
    private List<List<String>> bids;

}
