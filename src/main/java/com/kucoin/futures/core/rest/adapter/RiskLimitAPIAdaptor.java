package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interceptor.FuturesApiKey;
import com.kucoin.futures.core.rest.interfaces.RiskLimitAPI;
import com.kucoin.futures.core.rest.interfaces.retrofit.RiskLimitAPIRetrofit;
import com.kucoin.futures.core.rest.request.RiskLimitLevelUpdateRequest;
import com.kucoin.futures.core.rest.response.RiskLimitResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
public class RiskLimitAPIAdaptor extends AuthRetrofitAPIImpl<RiskLimitAPIRetrofit> implements RiskLimitAPI {
    public RiskLimitAPIAdaptor(String baseUrl, FuturesApiKey apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Override
    public List<RiskLimitResponse> getRiskLimit(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getRiskLimit(symbol));
    }

    @Override
    public Boolean changeRiskLimit(String symbol, Integer level) throws IOException {
        RiskLimitLevelUpdateRequest request = new RiskLimitLevelUpdateRequest();
        request.setSymbol(symbol);
        request.setLevel(level);
        return super.executeSync(getAPIImpl().changeRiskLimit(request));
    }
}
