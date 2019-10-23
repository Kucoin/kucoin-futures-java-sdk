/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.model.Level2Message;
import com.kumex.core.model.Level3Message;
import com.kumex.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.OrderBookAPI;
import com.kumex.core.rest.interfaces.retrofit.OrderBookAPIRetrofit;
import com.kumex.core.rest.response.OrderBookResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public class OrderBookAPIAdapter extends PublicRetrofitAPIImpl<OrderBookAPIRetrofit> implements OrderBookAPI {

    public OrderBookAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public OrderBookResponse getFullLevel2OrderBook(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getFullLevel2OrderBook(symbol));
    }

    @Override
    public List<Level2Message> getLevel2PullingMessages(String symbol, long start, long end) throws IOException {
        return super.executeSync(getAPIImpl().getLevel2PullingMessages(symbol, start, end));
    }

    @Override
    public OrderBookResponse getFullLevel3OrderBook(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getFullLevel3OrderBook(symbol));
    }

    @Override
    public List<Level3Message> getLevel3PullingMessages(String symbol, long start, long end) throws IOException {
        return super.executeSync(getAPIImpl().getLevel3PullingMessages(symbol, start, end));
    }
}
