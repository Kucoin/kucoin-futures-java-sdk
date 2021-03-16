/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interceptor.FuturesApiKey;
import com.kucoin.futures.core.rest.interfaces.DepositAPI;
import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.response.DepositAddressResponse;
import com.kucoin.futures.core.rest.response.Pagination;
import com.kucoin.futures.core.rest.interfaces.retrofit.DepositAPIRetrofit;
import com.kucoin.futures.core.rest.response.DepositResponse;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class DepositAPIAdapter extends AuthRetrofitAPIImpl<DepositAPIRetrofit> implements DepositAPI {

    public DepositAPIAdapter(String baseUrl, FuturesApiKey apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }


    @Override
    public DepositAddressResponse getDepositAddress(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getDepositAddress(currency));
    }

    @Override
    public Pagination<DepositResponse> getDepositList(String status, String currency, DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return super.executeSync(getAPIImpl().getDepositPageList(request.getCurrentPage(), request.getPageSize(), status,
                currency, request.getStarAt(), request.getEndAt()));
    }
}
