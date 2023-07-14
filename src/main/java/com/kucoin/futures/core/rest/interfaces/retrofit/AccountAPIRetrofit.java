/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.request.SubApiKeyCreateRequest;
import com.kucoin.futures.core.rest.request.SubApiKeyUpdateRequest;
import com.kucoin.futures.core.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface AccountAPIRetrofit {

    @GET("api/v1/account-overview")
    Call<KucoinFuturesResponse<AccountOverviewResponse>> accountOverview(@Query("currency") String currency);

    @GET("api/v1/account-overview-all")
    Call<KucoinFuturesResponse<AccountOverviewAllResponse>> accountOverviewAll(@Query("currency") String currency);

    @GET("api/v1/transaction-history")
    Call<KucoinFuturesResponse<HasMoreResponse<TransactionHistory>>> transactionHistory(@Query("startAt") Long startAt,
                                                                                @Query("endAt") Long endAt,
                                                                                @Query("type") String type,
                                                                                @Query("offset") Long offset,
                                                                                @Query("maxCount") Long maxCount,
                                                                                @Query("currency") String currency);


    @GET("api/v1/sub/api-key")
    Call<KucoinFuturesResponse<List<SubApiKeyResponse>>> getSubApiKey(@Query("subName") String subName, @Query("apiKey") String apiKey);

    @POST("api/v1/sub/api-key")
    Call<KucoinFuturesResponse<SubApiKeyResponse>> createSubApiKey(@Body SubApiKeyCreateRequest request);

    @POST("api/v1/sub/api-key/update")
    Call<KucoinFuturesResponse<SubApiKeyResponse>> updateSubApiKey(@Body SubApiKeyUpdateRequest request);

    @DELETE("api/v1/sub/api-key")
    Call<KucoinFuturesResponse<SubApiKeyResponse>> deleteSubApiKey(@QueryMap Map<String, String> params);

}
