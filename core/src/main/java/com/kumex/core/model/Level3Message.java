/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level3Message {

    private String symbol;

    private Long sequence;

    private String side;

    private Long orderTime;

    private Long size;

    private String orderId;

    private BigDecimal price;

    private String type;

    private String clientOid;

    private Long ts;

}
