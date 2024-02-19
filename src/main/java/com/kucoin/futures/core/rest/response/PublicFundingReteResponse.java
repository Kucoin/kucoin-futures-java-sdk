package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class PublicFundingReteResponse {
    private String symbol;
    private BigDecimal fundingRate;
    private long timepoint;
}
