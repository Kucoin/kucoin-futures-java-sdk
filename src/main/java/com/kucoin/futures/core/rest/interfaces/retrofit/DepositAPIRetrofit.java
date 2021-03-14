/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.DepositAddressResponse;
import com.kucoin.futures.core.rest.response.DepositResponse;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.Pagination;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface DepositAPIRetrofit {

    @GET("api/v1/deposit-address")
    Call<KucoinFuturesResponse<DepositAddressResponse>> getDepositAddress(@Query("currency") String currency);

    @GET("api/v1/deposit-list")
    Call<KucoinFuturesResponse<Pagination<DepositResponse>>> getDepositPageList(@Query("currentPage") int currentPage,
                                                                        @Query("pageSize") int pageSize,
                                                                        @Query("status") String status,
                                                                        @Query("currency") String currency,
                                                                        @Query("startAt") Long startAt,
                                                                        @Query("endAt") Long endAt);
}
