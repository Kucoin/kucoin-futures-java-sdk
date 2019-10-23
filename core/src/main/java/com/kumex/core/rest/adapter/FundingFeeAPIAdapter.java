/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.FundingFeeAPI;
import com.kumex.core.rest.interfaces.retrofit.FundingFeeAPIRetrofit;
import com.kumex.core.rest.request.DuringHasMoreRequest;
import com.kumex.core.rest.response.FundingHistoryResponse;
import com.kumex.core.rest.response.HasMoreResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/14
 */
public class FundingFeeAPIAdapter extends AuthRetrofitAPIImpl<FundingFeeAPIRetrofit> implements FundingFeeAPI {

    public FundingFeeAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public HasMoreResponse<FundingHistoryResponse> getFundingHistory(String symbol, Boolean reverse, Boolean forward,
                                                                     DuringHasMoreRequest request) throws IOException {
        if (request == null) request = DuringHasMoreRequest.builder().build();
        return super.executeSync(getAPIImpl().getFundingHistory(symbol, reverse, forward, request.getStarAt(),
                request.getEndAt(), request.getOffset(), request.getMaxCount()));
    }
}
