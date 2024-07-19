/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.interceptor.FuturesApiKey;
import com.kucoin.futures.core.rest.interfaces.PositionAPI;
import com.kucoin.futures.core.rest.request.AddMarginManuallyRequest;
import com.kucoin.futures.core.rest.request.HistoryPositionsRequest;
import com.kucoin.futures.core.rest.request.UpdateAutoDepositMarginRequest;
import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.retrofit.PositionAPIRetrofit;
import com.kucoin.futures.core.rest.request.WithdrawMarginRequest;
import com.kucoin.futures.core.rest.response.HistoryPositionResponse;
import com.kucoin.futures.core.rest.response.Pagination;
import com.kucoin.futures.core.rest.response.PositionResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenshiwei
 * @since 2019/10/14
 */
public class PositionAPIAdapter extends AuthRetrofitAPIImpl<PositionAPIRetrofit> implements PositionAPI {

    public PositionAPIAdapter(String baseUrl, FuturesApiKey apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Override
    public PositionResponse getPosition(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getPosition(symbol));
    }

    @Override
    public List<PositionResponse> getPositions() throws IOException {
        return super.executeSync(getAPIImpl().getPositions());
    }

    @Override
    public Pagination<HistoryPositionResponse> getHistoryPositions(HistoryPositionsRequest request) throws IOException {
        return super.executeSync(getAPIImpl().getHistoryPositions(request.getSymbol(),
                request.getFrom(),
                request.getTo(),
                request.getLimit(),
                request.getPageId())
        );
    }

    @Override
    public void setAutoDepositMargin(String symbol, boolean status) throws IOException {
        UpdateAutoDepositMarginRequest request = UpdateAutoDepositMarginRequest.builder().status(status).symbol(symbol).build();
        super.executeSync(getAPIImpl().setAutoDepositMargin(request));
    }

    @Override
    public BigDecimal getMaxWithdrawMargin(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getMaxWithdrawMargin(symbol));
    }

    @Override
    public BigDecimal withdrawMargin(WithdrawMarginRequest request) throws IOException {
        return super.executeSync(getAPIImpl().withdrawMargin(request));
    }

    @Override
    public void addMarginManually(String symbol, BigDecimal margin, String bizNo) throws IOException {
        AddMarginManuallyRequest request = AddMarginManuallyRequest.builder().symbol(symbol).margin(margin).bizNo(bizNo).build();
        super.executeSync(getAPIImpl().addMarginManually(request));
    }
}
