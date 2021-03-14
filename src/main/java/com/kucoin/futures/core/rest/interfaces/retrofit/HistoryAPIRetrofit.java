/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.TransactionHistoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface HistoryAPIRetrofit {

    @GET("api/v1/trade/history")
    Call<KucoinFuturesResponse<List<TransactionHistoryResponse>>> getTransactionHistories(@Query("symbol") String symbol);

}
