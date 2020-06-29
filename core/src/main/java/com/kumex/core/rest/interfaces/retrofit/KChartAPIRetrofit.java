/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.KumexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/28
 */
public interface KChartAPIRetrofit {

    @GET("api/v1/kline/query")
    Call<KumexResponse<List<List<String>>>> getKChart(@Query("symbol") String symbol, @Query("granularity") int granularity,
                                                      @Query("from") Long from, @Query("to") Long to);

}
