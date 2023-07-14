/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.interceptor.FuturesApiKey;
import com.kucoin.futures.core.rest.interfaces.retrofit.AccountAPIRetrofit;
import com.kucoin.futures.core.rest.request.SubApiKeyCreateRequest;
import com.kucoin.futures.core.rest.request.SubApiKeyUpdateRequest;
import com.kucoin.futures.core.rest.response.*;
import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.AccountAPI;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshiwei
 * @since 2019/7/26
 */
public class AccountAPIAdapter extends AuthRetrofitAPIImpl<AccountAPIRetrofit> implements AccountAPI {

    public AccountAPIAdapter(String baseUrl, FuturesApiKey apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Override
    public AccountOverviewResponse accountOverview(String currency) throws IOException {
        return super.executeSync(getAPIImpl().accountOverview(currency));
    }

    @Override
    public AccountOverviewAllResponse accountOverviewAll(String currency) throws IOException {
        return super.executeSync(getAPIImpl().accountOverviewAll(currency));
    }

    @Override
    public HasMoreResponse<TransactionHistory> transactionHistory(String type, String currency, DuringHasMoreRequest request) throws IOException {
        if (request == null) request = DuringHasMoreRequest.builder().build();
        return super.executeSync(getAPIImpl().transactionHistory(request.getStarAt(), request.getEndAt(), type,
                request.getOffset(), request.getMaxCount(), currency));
    }

    @Override
    public List<SubApiKeyResponse> getSubApiKey(String subName, String apiKey) throws IOException {
        return super.executeSync(getAPIImpl().getSubApiKey(subName, apiKey));
    }

    @Override
    public SubApiKeyResponse createSubApiKey(String subName, String passphrase, String remark, String permission, String ipWhitelist, String expire) throws IOException {
        SubApiKeyCreateRequest request = new SubApiKeyCreateRequest();
        request.setSubName(subName);
        request.setPassphrase(passphrase);
        request.setRemark(remark);
        request.setPermission(permission);
        request.setIpWhitelist(ipWhitelist);
        request.setExpire(expire);
        return super.executeSync(getAPIImpl().createSubApiKey(request));
    }

    @Override
    public SubApiKeyResponse updateSubApiKey(String subName, String apiKey, String passphrase, String permission, String ipWhitelist, String expire) throws IOException {
        SubApiKeyUpdateRequest request = new SubApiKeyUpdateRequest();
        request.setSubName(subName);
        request.setPassphrase(passphrase);
        request.setApiKey(apiKey);
        request.setPermission(permission);
        request.setIpWhitelist(ipWhitelist);
        request.setExpire(expire);
        return super.executeSync(getAPIImpl().updateSubApiKey(request));
    }

    @Override
    public SubApiKeyResponse deleteSubApiKey(String subName, String apiKey, String passphrase) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("subName", subName);
        params.put("apiKey", apiKey);
        params.put("passphrase", passphrase);
        return super.executeSync(getAPIImpl().deleteSubApiKey(params));
    }
}
