/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @since 2019/10/15
 */
@Data
public class FundingRateResponse extends IndexRateResponse {

    private BigDecimal predictedValue;

}
