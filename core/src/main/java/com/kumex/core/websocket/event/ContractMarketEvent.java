/*
 * Copyright 2019 Mek Global Limited.
 */

package com.kumex.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/18
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractMarketEvent {

    private BigDecimal granularity;

    private BigDecimal indexPrice;

    private BigDecimal markPrice;

    private long timestamp;

}
