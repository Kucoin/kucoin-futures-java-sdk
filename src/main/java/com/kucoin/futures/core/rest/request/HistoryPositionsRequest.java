/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author blaze.tan
 */
@Data
@Builder
public class HistoryPositionsRequest {

    /**
     * Symbol of the contract
     */
    private String symbol;

    /**
     * Closing start time
     */
    private Long from;

    /**
     * Closing end time
     */
    private Long to;

    /**
     * Number of requests per page, max 200, default 10
     */
    private Integer limit = 10;

    /**
     * Current page number, default 1
     */
    private Integer pageId = 1;

}
