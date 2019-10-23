/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.WithdrawalAPI;
import com.kumex.core.rest.interfaces.retrofit.WithdrawalAPIRetrofit;
import com.kumex.core.rest.request.DuringPageRequest;
import com.kumex.core.rest.request.WithdrawApplyRequest;
import com.kumex.core.rest.response.Pagination;
import com.kumex.core.rest.response.WithdrawApplyResponse;
import com.kumex.core.rest.response.WithdrawQuotaResponse;
import com.kumex.core.rest.response.WithdrawResponse;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class WithdrawalAPIAdapter extends AuthRetrofitAPIImpl<WithdrawalAPIRetrofit> implements WithdrawalAPI {

    public WithdrawalAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public WithdrawQuotaResponse getWithdrawQuotas(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getWithdrawQuotas(currency));
    }

    @Override
    public WithdrawApplyResponse withdrawFunds(WithdrawApplyRequest request) throws IOException {
        return super.executeSync(getAPIImpl().applyWithdraw(request));
    }

    @Override
    public void cancelWithdraw(String withdrawalId) throws IOException {
        super.executeSync(getAPIImpl().cancelWithdraw(withdrawalId));
    }

    @Override
    public Pagination<WithdrawResponse> getWithdrawList(String status, DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return super.executeSync(getAPIImpl().getWithdrawPageList(request.getCurrentPage(), request.getPageSize(), status,
                request.getStarAt(), request.getEndAt()));
    }
}
