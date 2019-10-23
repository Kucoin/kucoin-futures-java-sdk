/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.request.AddMarginManuallyRequest;
import com.kumex.core.rest.request.UpdateAutoDepositMarginRequest;
import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.PositionResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/14
 */
public interface PositionAPIRetrofit {

    @GET("api/v1/position")
    Call<KumexResponse<PositionResponse>> getPosition(@Query("symbol") String symbol);

    @GET("api/v1/positions")
    Call<KumexResponse<List<PositionResponse>>> getPositions();

    @POST("api/v1/position/margin/auto-deposit-status")
    Call<KumexResponse<Object>> setAutoDepositMargin(@Body UpdateAutoDepositMarginRequest request);

    @POST("api/v1/position/margin/deposit-margin")
    Call<KumexResponse<Object>> addMarginManually(@Body AddMarginManuallyRequest request);

}
