/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.OrderAPI;
import com.kumex.core.rest.interfaces.retrofit.OrderAPIRetrofit;
import com.kumex.core.rest.request.DuringPageRequest;
import com.kumex.core.rest.request.OrderCreateApiRequest;
import com.kumex.core.rest.response.OrderCancelResponse;
import com.kumex.core.rest.response.OrderCreateResponse;
import com.kumex.core.rest.response.OrderResponse;
import com.kumex.core.rest.response.Pagination;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class OrderAPIAdapter extends AuthRetrofitAPIImpl<OrderAPIRetrofit> implements OrderAPI {

    public OrderAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public OrderCreateResponse createOrder(OrderCreateApiRequest opsRequest) throws IOException {
        return executeSync(getAPIImpl().createOrder(opsRequest));
    }

    @Override
    public OrderCancelResponse cancelOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().cancelOrder(orderId));
    }

    @Override
    public OrderCancelResponse cancelAllLimitOrders(String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelLimitOrders(symbol));
    }

    @Override
    public OrderCancelResponse cancelAllStopOrders(String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelStopOrders(symbol));
    }

    @Override
    public OrderResponse getOrderDetail(String orderId) throws IOException {
        return executeSync(getAPIImpl().getOrder(orderId));
    }

    @Override
    public Pagination<OrderResponse> getOrderList(String symbol, String side, String type,
                                                  String status, DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return executeSync(getAPIImpl().queryOrders(symbol, side, type, status,
                request.getStarAt(), request.getEndAt(), request.getPageSize(), request.getCurrentPage()));
    }

    @Override
    public Pagination<OrderResponse> getUntriggeredStopOrderList(String symbol, String side, String type,
                                                                 DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return executeSync(getAPIImpl().queryStopOrders(symbol, side, type,
                request.getStarAt(), request.getEndAt(), request.getPageSize(), request.getCurrentPage()));
    }

    @Override
    public List<OrderResponse> getRecentDoneOrders() throws IOException {
        return executeSync(getAPIImpl().queryRecentDoneOrders());
    }
}
