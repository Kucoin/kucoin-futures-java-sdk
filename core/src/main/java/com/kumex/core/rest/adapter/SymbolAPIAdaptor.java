/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.SymbolAPI;
import com.kumex.core.rest.interfaces.retrofit.SymbolAPIRetrofit;
import com.kumex.core.rest.response.ContractResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class SymbolAPIAdaptor extends PublicRetrofitAPIImpl<SymbolAPIRetrofit> implements SymbolAPI {

    public SymbolAPIAdaptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<ContractResponse> getOpenContractList() throws IOException {
        return super.executeSync(getAPIImpl().getOpenContractList());
    }

    @Override
    public ContractResponse getContract(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getContract(symbol));
    }
}
