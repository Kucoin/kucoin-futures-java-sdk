/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.PositionAPI;
import com.kumex.core.rest.interfaces.retrofit.PositionAPIRetrofit;
import com.kumex.core.rest.request.AddMarginManuallyRequest;
import com.kumex.core.rest.request.UpdateAutoDepositMarginRequest;
import com.kumex.core.rest.response.PositionResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/14
 */
public class PositionAPIAdapter extends AuthRetrofitAPIImpl<PositionAPIRetrofit> implements PositionAPI {

    public PositionAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
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
    public void setAutoDepositMargin(String symbol, boolean status) throws IOException {
        UpdateAutoDepositMarginRequest request = UpdateAutoDepositMarginRequest.builder().status(status).symbol(symbol).build();
        super.executeSync(getAPIImpl().setAutoDepositMargin(request));
    }

    @Override
    public void addMarginManually(String symbol, BigDecimal margin, String bizNo) throws IOException {
        AddMarginManuallyRequest request = AddMarginManuallyRequest.builder().symbol(symbol).margin(margin).bizNo(bizNo).build();
        super.executeSync(getAPIImpl().addMarginManually(request));
    }
}
