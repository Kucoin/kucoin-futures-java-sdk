/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by chenshiwei on 2019/1/23.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountChangeEvent {

    private BigDecimal orderMargin;

    private BigDecimal availableBalance;

    private BigDecimal withdrawHold;

    private String currency;

    private long timestamp;

}
