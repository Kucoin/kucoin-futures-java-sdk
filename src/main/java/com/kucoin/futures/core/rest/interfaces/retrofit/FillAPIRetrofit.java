/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.ActiveOrderResponse;
import com.kucoin.futures.core.rest.response.FillResponse;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.Pagination;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface FillAPIRetrofit {

    @GET(value = "api/v1/fills")
    Call<KucoinFuturesResponse<Pagination<FillResponse>>> getFills(@Query("symbol") String symbol,
                                                           @Query("orderId") String orderId,
                                                           @Query("side") String side,
                                                           @Query("type") String type,
                                                           @Query("startAt") Long startAt,
                                                           @Query("endAt") Long endAt,
                                                           @Query("pageSize") int pageSize,
                                                           @Query("currentPage") int currentPage);

    @GET(value = "api/v1/recentFills")
    Call<KucoinFuturesResponse<List<FillResponse>>> recentFills();

    @GET(value = "api/v1/openOrderStatistics")
    Call<KucoinFuturesResponse<ActiveOrderResponse>> calActiveOrderValue(@Query("symbol") String symbol);
}
