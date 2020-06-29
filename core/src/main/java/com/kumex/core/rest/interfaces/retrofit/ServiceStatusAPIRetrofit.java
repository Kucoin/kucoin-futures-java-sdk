/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.ServiceStatusResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/29
 */
public interface ServiceStatusAPIRetrofit {

    @GET("api/v1/status")
    Call<KumexResponse<ServiceStatusResponse>> getServiceStatus();

}
