/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/7/1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level3MessageV2 {

    private String symbol;

    private Long sequence;

    private String side;

    private BigDecimal price;

    private Long size;

    private String orderId;

    private String clientOid;

    private Long orderTime;

    private Long ts;

    private String tradeId;

    private String makerOrderId;

    private String takerOrderId;

}
