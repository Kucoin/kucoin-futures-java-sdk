/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.FillAPI;
import com.kucoin.futures.core.rest.interfaces.retrofit.FillAPIRetrofit;
import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.response.ActiveOrderResponse;
import com.kucoin.futures.core.rest.response.Pagination;
import com.kucoin.futures.core.rest.response.FillResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class FillAPIAdapter extends AuthRetrofitAPIImpl<FillAPIRetrofit> implements FillAPI {

    public FillAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public Pagination<FillResponse> getFills(String symbol, String orderId, String side,
                                             String type, DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return executeSync(getAPIImpl().getFills(symbol, orderId, side, type, request.getStarAt(), request.getEndAt(),
                request.getPageSize(), request.getCurrentPage()));
    }

    @Override
    public List<FillResponse> recentFills() throws IOException {
        return executeSync(getAPIImpl().recentFills());
    }

    @Override
    public ActiveOrderResponse calActiveOrderValue(String symbol) throws IOException {
        return executeSync(getAPIImpl().calActiveOrderValue(symbol));
    }
}
