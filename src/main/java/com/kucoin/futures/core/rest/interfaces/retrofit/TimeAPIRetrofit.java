/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public interface TimeAPIRetrofit {

    @GET("api/v1/timestamp")
    Call<KucoinFuturesResponse<Long>> getServerTimeStamp();

}
