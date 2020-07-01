/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.request.WithdrawApplyRequest;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.Pagination;
import com.kucoin.futures.core.rest.response.WithdrawApplyResponse;
import com.kucoin.futures.core.rest.response.WithdrawQuotaResponse;
import com.kucoin.futures.core.rest.response.WithdrawResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface WithdrawalAPIRetrofit {

    @GET("api/v1/withdrawals/quotas")
    Call<KucoinFuturesResponse<WithdrawQuotaResponse>> getWithdrawQuotas(@Query("currency") String currency);

    @POST("api/v1/withdrawals")
    Call<KucoinFuturesResponse<WithdrawApplyResponse>> applyWithdraw(@Body WithdrawApplyRequest request);

    @DELETE("api/v1/withdrawals/{withdrawalId}")
    Call<KucoinFuturesResponse<Void>> cancelWithdraw(@Path("withdrawalId") String  withdrawalId);

    @GET("api/v1/withdrawal-list")
    Call<KucoinFuturesResponse<Pagination<WithdrawResponse>>> getWithdrawPageList(@Query("currentPage") int currentPage,
                                                                          @Query("pageSize") int pageSize,
                                                                          @Query("status") String status,
                                                                          @Query("currency") String currency,
                                                                          @Query("startAt") Long startAt,
                                                                          @Query("endAt") Long endAt);
}
