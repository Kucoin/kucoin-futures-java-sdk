/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.request.WithdrawApplyRequest;
import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.Pagination;
import com.kumex.core.rest.response.WithdrawApplyResponse;
import com.kumex.core.rest.response.WithdrawQuotaResponse;
import com.kumex.core.rest.response.WithdrawResponse;
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
    Call<KumexResponse<WithdrawQuotaResponse>> getWithdrawQuotas(@Query("currency") String currency);

    @POST("api/v1/withdrawals")
    Call<KumexResponse<WithdrawApplyResponse>> applyWithdraw(@Body WithdrawApplyRequest request);

    @DELETE("api/v1/withdrawals/{withdrawalId}")
    Call<KumexResponse<Void>> cancelWithdraw(@Path("withdrawalId") String  withdrawalId);

    @GET("api/v1/withdrawal-list")
    Call<KumexResponse<Pagination<WithdrawResponse>>> getWithdrawPageList(@Query("currentPage") int currentPage,
                                                                          @Query("pageSize") int pageSize,
                                                                          @Query("status") String status,
                                                                          @Query("currency") String currency,
                                                                          @Query("startAt") Long startAt,
                                                                          @Query("endAt") Long endAt);
}
