/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.TickerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
public interface TickerAPIRetrofit {

    @GET("api/v1/ticker")
    Call<KumexResponse<TickerResponse>> getTicker(@Query("symbol") String symbol);

}
