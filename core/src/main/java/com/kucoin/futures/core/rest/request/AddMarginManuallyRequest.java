/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/17
 */
@Data
@Builder
public class AddMarginManuallyRequest {

    private String symbol;

    private BigDecimal margin;

    private String bizNo;

}
