/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.model.Level2Message;
import com.kucoin.futures.core.model.Level3Message;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.OrderBookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface OrderBookAPIRetrofit {

    @GET("api/v1/level2/snapshot")
    Call<KucoinFuturesResponse<OrderBookResponse>> getFullLevel2OrderBook(@Query("symbol") String symbol);

    @GET("api/v1/level2/depth20")
    Call<KucoinFuturesResponse<OrderBookResponse>> getDepth20Level2OrderBook(@Query("symbol") String symbol);

    @GET("api/v1/level2/depth100")
    Call<KucoinFuturesResponse<OrderBookResponse>> getDepth100Level2OrderBook(@Query("symbol") String symbol);

    @GET("api/v1/level2/message/query")
    @Deprecated
    Call<KucoinFuturesResponse<List<Level2Message>>> getLevel2PullingMessages(@Query("symbol") String symbol,
                                                                      @Query("start") long start,
                                                                      @Query("end") long end);

    @GET("api/v1/level3/snapshot")
    Call<KucoinFuturesResponse<OrderBookResponse>> getFullLevel3OrderBookV1(@Query("symbol") String symbol);

    @GET("api/v2/level3/snapshot")
    Call<KucoinFuturesResponse<OrderBookResponse>> getFullLevel3OrderBook(@Query("symbol") String symbol);

    @GET("api/v1/level3/message/query")
    @Deprecated
    Call<KucoinFuturesResponse<List<Level3Message>>> getLevel3PullingMessages(@Query("symbol") String symbol,
                                                                      @Query("start") long start,
                                                                      @Query("end") long end);
}
