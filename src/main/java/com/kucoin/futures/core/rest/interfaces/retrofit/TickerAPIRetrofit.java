/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.TickerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author chenshiwei
 * @since 2019/10/15
 */
public interface TickerAPIRetrofit {

    @GET("api/v1/ticker")
    Call<KucoinFuturesResponse<TickerResponse>> getTicker(@Query("symbol") String symbol);

}
