/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author chenshiwei
 * @since 2019/10/15
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level2Message {

    private String symbol;

    private Long sequence;

    /**
     * price,side,size
     */
    private String change;

}
