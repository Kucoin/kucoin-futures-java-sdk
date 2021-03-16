/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.ContractResponse;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/11.
 */
public interface SymbolAPIRetrofit {

    @GET("api/v1/contracts/active")
    Call<KucoinFuturesResponse<List<ContractResponse>>> getOpenContractList();

    @GET("api/v1/contracts/{symbol}")
    Call<KucoinFuturesResponse<ContractResponse>> getContract(@Path("symbol") String symbol);

}
