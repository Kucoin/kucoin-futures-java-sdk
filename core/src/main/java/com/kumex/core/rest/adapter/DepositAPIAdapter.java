/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.DepositAPI;
import com.kumex.core.rest.interfaces.retrofit.DepositAPIRetrofit;
import com.kumex.core.rest.request.DuringPageRequest;
import com.kumex.core.rest.response.DepositAddressResponse;
import com.kumex.core.rest.response.DepositResponse;
import com.kumex.core.rest.response.Pagination;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class DepositAPIAdapter extends AuthRetrofitAPIImpl<DepositAPIRetrofit> implements DepositAPI {

    public DepositAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }


    @Override
    public DepositAddressResponse getDepositAddress(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getDepositAddress(currency));
    }

    @Override
    public Pagination<DepositResponse> getDepositList(String status, DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return super.executeSync(getAPIImpl().getDepositPageList(request.getCurrentPage(), request.getPageSize(), status,
                request.getStarAt(), request.getEndAt()));
    }
}
