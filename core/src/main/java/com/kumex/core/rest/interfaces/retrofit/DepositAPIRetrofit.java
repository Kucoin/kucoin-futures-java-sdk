/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.DepositAddressResponse;
import com.kumex.core.rest.response.DepositResponse;
import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.Pagination;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface DepositAPIRetrofit {

    @GET("api/v1/deposit-address")
    Call<KumexResponse<DepositAddressResponse>> getDepositAddress(@Query("currency") String currency);

    @GET("api/v1/deposit-list")
    Call<KumexResponse<Pagination<DepositResponse>>> getDepositPageList(@Query("currentPage") int currentPage,
                                                                        @Query("pageSize") int pageSize,
                                                                        @Query("status") String status,
                                                                        @Query("startAt") Long startAt,
                                                                        @Query("endAt") Long endAt);
}
