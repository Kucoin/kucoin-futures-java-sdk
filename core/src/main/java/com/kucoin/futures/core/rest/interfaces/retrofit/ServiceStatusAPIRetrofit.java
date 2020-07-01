/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.ServiceStatusResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/29
 */
public interface ServiceStatusAPIRetrofit {

    @GET("api/v1/status")
    Call<KucoinFuturesResponse<ServiceStatusResponse>> getServiceStatus();

}
