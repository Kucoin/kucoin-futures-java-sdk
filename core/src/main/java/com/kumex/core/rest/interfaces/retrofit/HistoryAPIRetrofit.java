/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.TransactionHistoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface HistoryAPIRetrofit {

    @GET("api/v1/trade/history")
    Call<KumexResponse<List<TransactionHistoryResponse>>> getTransactionHistories(@Query("symbol") String symbol);

}
