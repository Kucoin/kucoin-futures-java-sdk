/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author chenshiwei
 * @since 2020/6/28
 */
public interface KChartAPIRetrofit {

    @GET("api/v1/kline/query")
    Call<KucoinFuturesResponse<List<List<String>>>> getKChart(@Query("symbol") String symbol, @Query("granularity") int granularity,
                                                      @Query("from") Long from, @Query("to") Long to);

}
