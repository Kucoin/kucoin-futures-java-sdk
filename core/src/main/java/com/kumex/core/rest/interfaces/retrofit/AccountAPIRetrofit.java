/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.AccountOverviewResponse;
import com.kumex.core.rest.response.HasMoreResponse;
import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.TransactionHistory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface AccountAPIRetrofit {

    @GET("api/v1/account-overview")
    Call<KumexResponse<AccountOverviewResponse>> accountOverview();

    @GET("api/v1/transaction-history")
    Call<KumexResponse<HasMoreResponse<TransactionHistory>>> transactionHistory(@Query("startAt") Long startAt,
                                                                                @Query("endAt") Long endAt,
                                                                                @Query("type") String type,
                                                                                @Query("offset") Long offset,
                                                                                @Query("maxCount") Long maxCount);
}
