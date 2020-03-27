/**
 * Copyright 2019 Mek Global Limited
 */
package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by universe on 2019年4月26日.
 */
@Data
@JsonInclude(Include.NON_NULL)
public class FundingHistoryResponse implements Serializable {
    
    private static final long serialVersionUID = -8467479879064794955L;

    private Long id;

    private String symbol;

    private long timePoint;

    private BigDecimal fundingRate;

    private BigDecimal markPrice;

    private Long positionQty;

    private BigDecimal positionCost;

    private BigDecimal funding;

    private String settleCurrency;

    private String context;
    
}
