/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
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
