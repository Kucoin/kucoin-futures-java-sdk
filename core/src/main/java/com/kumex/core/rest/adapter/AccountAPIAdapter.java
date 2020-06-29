/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.AccountAPI;
import com.kumex.core.rest.interfaces.retrofit.AccountAPIRetrofit;
import com.kumex.core.rest.request.DuringHasMoreRequest;
import com.kumex.core.rest.response.AccountOverviewResponse;
import com.kumex.core.rest.response.HasMoreResponse;
import com.kumex.core.rest.response.TransactionHistory;

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
