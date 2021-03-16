/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 订单创建对象
 *
 * @author 屈亮
 * @since 2018-09-17
 */
@Getter
@Builder
public class OrderCreateApiRequest {

    /**
     * Unique order id selected by you to identify your order e.g. UUID
     */
    private String clientOid;

    /**
     * buy or sell
     */
    private String side;

    /**
     * a valid trading symbol code. e.g. XBTUSDM
     */
    private String symbol;

    /**
     * [optional] limit or market (default is limit)
     */
    @Builder.Default
    private String type = "limit";

    /**
     * Leverage of the order
     */
    private String leverage;

    /**
     * [optional] remark for the order, length cannot exceed 100 utf8 characters
     */
    private String remark;

    /**
     * [optional] Either down or up. Requires stopPrice and stopPriceType to be defined
     */
    @Builder.Default
    private String stop = "";

    /**
     * [optional] Either TP, IP or MP, Need to be defined if stop is specified.
     */
    private String stopPriceType;

    /**
     * [optional] Need to be defined if stop is specified.
     */
    private BigDecimal stopPrice;

    /**
     * [optional] self trade protect , CN, CO, CB or DC
     */
    @Builder.Default
    private String stp = "";

    /**
     * [optional] A mark to reduce the position size only. Set to false by default.
     */
    private boolean reduceOnly = false;

    /**
     * [optional] A mark to close the position. Set to false by default.
     */
    private boolean closeOrder = false;

    /**
     * [optional] A mark to forcely hold the funds for an order, even though it's an order to reduce the position size.
     * This helps the order stay on the order book and not get canceled when the position size changes. Set to false by default.
     */
    private boolean forceHold = false;

    /**
     * price per base currency
     */
    private BigDecimal price;

    /**
     * amount of base currency to buy or sell
     */
    private BigDecimal size;

    /**
     * [optional] GTC, IOC(default is GTC), read Time In Force
     */
    @Builder.Default
    private String timeInForce = "GTC";

    /**
     * [optional] Post only flag, invalid when timeInForce is IOC.
     * When postOnly chose, not allowed choose hidden or iceberg.
     */
    private boolean postOnly;

    /**
     * [optional] Orders not displaying in order book. When hidden chose, not allowed choose postOnly.
     */
    private boolean hidden;

    /**
     * [optional] Only visible portion of the order is displayed in the order book.
     * When iceberg chose, not allowed choose postOnly.
     */
    private boolean iceberg;

    /**
     * [optional] The maximum visible size of an iceberg order
     */
    private BigDecimal visibleSize;

}