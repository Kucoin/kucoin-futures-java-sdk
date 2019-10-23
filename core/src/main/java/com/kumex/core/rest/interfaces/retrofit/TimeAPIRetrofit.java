/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.KumexResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public interface TimeAPIRetrofit {

    @GET("api/v1/timestamp")
    Call<KumexResponse<Long>> getServerTimeStamp();

}
