/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.util.List;

/**
 * Created by chenshiwei on 2019/2/22.
 */
@Data
public class AllTickersResponse {

    private Long time;

    private List<MarketTickerResponse> ticker;

}
