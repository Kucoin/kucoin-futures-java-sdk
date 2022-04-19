/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.AccountOverviewResponse;
import com.kucoin.futures.core.rest.response.HasMoreResponse;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.TransactionHistory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface AccountAPIRetrofit {

    @GET("api/v1/account-overview")
    Call<KucoinFuturesResponse<AccountOverviewResponse>> accountOverview(@Query("currency") String currency);

    @GET("api/v1/transaction-history")
    Call<KucoinFuturesResponse<HasMoreResponse<TransactionHistory>>> transactionHistory(@Query("startAt") Long startAt,
                                                                                        @Query("endAt") Long endAt,
                                                                                        @Query("type") String type,
                                                                                        @Query("offset") Long offset,
                                                                                        @Query("maxCount") Long maxCount,
                                                                                        @Query("currency") String currency,
                                                                                        @Query("forward") Boolean forward);
}
