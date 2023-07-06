/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @since 2019/10/15
 */
@Data
public class IndexItem {

    private String exchange;

    private BigDecimal price;

    private BigDecimal weight;

}
