/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.interfaces.retrofit.AccountAPIRetrofit;
import com.kucoin.futures.core.rest.response.AccountOverviewResponse;
import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.AccountAPI;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;
import com.kucoin.futures.core.rest.response.HasMoreResponse;
import com.kucoin.futures.core.rest.response.TransactionHistory;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/7/26
 */
public class AccountAPIAdapter extends AuthRetrofitAPIImpl<AccountAPIRetrofit> implements AccountAPI {

    public AccountAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public AccountOverviewResponse accountOverview(String currency) throws IOException {
        return super.executeSync(getAPIImpl().accountOverview(currency));
    }

    @Override
    public HasMoreResponse<TransactionHistory> transactionHistory(String type, String currency, DuringHasMoreRequest request) throws IOException {
        if (request == null) request = DuringHasMoreRequest.builder().build();
        return super.executeSync(getAPIImpl().transactionHistory(request.getStarAt(), request.getEndAt(), type,
                request.getOffset(), request.getMaxCount(), currency));
    }
}
