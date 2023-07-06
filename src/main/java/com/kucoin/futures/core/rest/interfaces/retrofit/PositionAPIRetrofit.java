/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.request.AddMarginManuallyRequest;
import com.kucoin.futures.core.rest.request.UpdateAutoDepositMarginRequest;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.PositionResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author chenshiwei
 * @since 2019/10/14
 */
public interface PositionAPIRetrofit {

    @GET("api/v1/position")
    Call<KucoinFuturesResponse<PositionResponse>> getPosition(@Query("symbol") String symbol);

    @GET("api/v1/positions")
    Call<KucoinFuturesResponse<List<PositionResponse>>> getPositions();

    @POST("api/v1/position/margin/auto-deposit-status")
    Call<KucoinFuturesResponse<Object>> setAutoDepositMargin(@Body UpdateAutoDepositMarginRequest request);

    @POST("api/v1/position/margin/deposit-margin")
    Call<KucoinFuturesResponse<Object>> addMarginManually(@Body AddMarginManuallyRequest request);

}
