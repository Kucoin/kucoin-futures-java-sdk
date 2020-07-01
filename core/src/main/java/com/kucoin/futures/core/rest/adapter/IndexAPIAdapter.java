/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.response.IndexResponse;
import com.kucoin.futures.core.rest.response.MarkPriceResponse;
import com.kucoin.futures.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.IndexAPI;
import com.kucoin.futures.core.rest.interfaces.retrofit.IndexAPIRetrofit;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;
import com.kucoin.futures.core.rest.response.FundingRateResponse;
import com.kucoin.futures.core.rest.response.HasMoreResponse;
import com.kucoin.futures.core.rest.response.IndexRateResponse;
import com.kucoin.futures.core.rest.response.InterestRateResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
public class IndexAPIAdapter extends PublicRetrofitAPIImpl<IndexAPIRetrofit> implements IndexAPI {

    public IndexAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public HasMoreResponse<InterestRateResponse> getInterestRateList(String symbol, Boolean reverse, Boolean forward,
                                                                     DuringHasMoreRequest request) throws IOException {
        if (request == null) {
            request = DuringHasMoreRequest.builder().build();
        }
        return super.executeSync(getAPIImpl().getInterestRateList(symbol, reverse, forward, request.getStarAt(),
                request.getEndAt(), request.getOffset(), request.getMaxCount()));
    }

    @Override
    public HasMoreResponse<IndexResponse> getIndexList(String symbol, Boolean reverse, Boolean forward, DuringHasMoreRequest request) throws IOException {
        if (request == null) {
            request = DuringHasMoreRequest.builder().build();
        }
        return super.executeSync(getAPIImpl().getIndexList(symbol, reverse, forward, request.getStarAt(),
                request.getEndAt(), request.getOffset(), request.getMaxCount()));
    }

    @Override
    public MarkPriceResponse getCurrentMarkPrice(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getCurrentMarkPrice(symbol));
    }

    @Override
    public HasMoreResponse<IndexRateResponse> getPremiumIndex(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getPremiumIndex(symbol));
    }

    @Override
    public FundingRateResponse getCurrentFundingRate(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getCurrentFundingRate(symbol));
    }
}
