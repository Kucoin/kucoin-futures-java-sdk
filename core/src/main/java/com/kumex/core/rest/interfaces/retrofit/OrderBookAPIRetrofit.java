/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.model.Level2Message;
import com.kumex.core.model.Level3Message;
import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.OrderBookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface OrderBookAPIRetrofit {

    @GET("api/v1/level2/snapshot")
    Call<KumexResponse<OrderBookResponse>> getFullLevel2OrderBook(@Query("symbol") String symbol);

    @GET("api/v1/level2/message/query")
    Call<KumexResponse<List<Level2Message>>> getLevel2PullingMessages(@Query("symbol") String symbol,
                                                                      @Query("start") long start,
                                                                      @Query("end") long end);

    @GET("api/v1/level3/snapshot")
    Call<KumexResponse<OrderBookResponse>> getFullLevel3OrderBook(@Query("symbol") String symbol);

    @GET("api/v1/level3/message/query")
    Call<KumexResponse<List<Level3Message>>> getLevel3PullingMessages(@Query("symbol") String symbol,
                                                                      @Query("start") long start,
                                                                      @Query("end") long end);
}
