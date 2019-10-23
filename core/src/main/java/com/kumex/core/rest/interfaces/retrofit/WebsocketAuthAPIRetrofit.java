/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.WebsocketTokenResponse;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public interface WebsocketAuthAPIRetrofit {

    @POST("api/v1/bullet-private")
    Call<KumexResponse<WebsocketTokenResponse>> getPrivateToken();

}
