/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interceptor.FuturesApiKey;
import com.kucoin.futures.core.rest.response.*;
import com.kucoin.futures.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.IndexAPI;
import com.kucoin.futures.core.rest.interfaces.retrofit.IndexAPIRetrofit;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;

import java.io.IOException;

/**
 * @author chenshiwei
 * @since 2019/10/15
 */
public class IndexAPIAdapter extends AuthRetrofitAPIImpl<IndexAPIRetrofit> implements IndexAPI {

    public IndexAPIAdapter(String baseUrl, FuturesApiKey apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
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

    @Override
    public TradeStatisticsResponse getTradeStatistics() throws IOException {
        return super.executeSync(getAPIImpl().getTradeStatistics());
    }

}
