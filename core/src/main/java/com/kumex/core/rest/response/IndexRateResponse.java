/*
 * Copyright 2019 Mek Global Limited.
 */

package com.kumex.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
@Data
public class IndexRateResponse {

    private String symbol;

    private Long granularity;

    private Long timePoint;

    private BigDecimal value;

}
