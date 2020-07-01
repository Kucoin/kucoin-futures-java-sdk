/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.ActiveOrderResponse;
import com.kucoin.futures.core.rest.response.FillResponse;
import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.response.Pagination;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface FillAPI {

    /**
     * Get a list of recent fills.
     *
     * @param symbol      [optional] Limit list of fills to this orderId
     * @param orderId     [optional] Limit list of fills to this orderId
     * @param side        [optional] buy or sell
     * @param type        [optional] limit, market, limit_stop or market_stop
     * @param request     [Optional] include startAt endAt offset and maxCount optional parameters
     * @return Trades.
     */
    Pagination<FillResponse> getFills(String symbol, String orderId, String side, String type,
                                      DuringPageRequest request) throws IOException;

    /**
     * Get a list of recent 1000 fills in the last 24 hours.
     * If you need to get your recent traded order history with low latency, you may query this endpoint.
     *
     * @return
     * @throws IOException
     */
    List<FillResponse> recentFills() throws IOException;

    /**
     * You can query this endpoint to get the the total number and value of the all your active orders.
     *
     * @param symbol      Symbol of the contract
     * @return
     */
    ActiveOrderResponse calActiveOrderValue(String symbol) throws IOException;
}
