/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.request.OrderCreateApiRequest;
import com.kucoin.futures.core.rest.response.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface OrderAPIRetrofit {

    @POST("api/v1/orders")
    Call<KucoinFuturesResponse<OrderCreateResponse>> createOrder(@Body OrderCreateApiRequest opsRequest);

    @POST("api/v1/orders/multi")
    Call<KucoinFuturesResponse<List<OrderCreateMultiResponse>>> createOrderMulti(@Body List<OrderCreateApiRequest> requests);

    @POST("api/v1/orders/test")
    Call<KucoinFuturesResponse<OrderCreateResponse>> createOrderTest(@Body OrderCreateApiRequest opsRequest);

    @DELETE("api/v1/orders/{orderId}")
    Call<KucoinFuturesResponse<OrderCancelResponse>> cancelOrder(@Path("orderId") String orderId);

    @DELETE("api/v1/orders/client-order/{clientOid}")
    Call<KucoinFuturesResponse<OrderCancelByClientOidResponse>> cancelOrderByClientOid(@Path("clientOid") String clientOid, @Query("symbol") String symbol);

    @DELETE("api/v1/orders")
    Call<KucoinFuturesResponse<OrderCancelResponse>> cancelLimitOrders(@Query("symbol") String symbol);

    @DELETE("api/v1/stopOrders")
    Call<KucoinFuturesResponse<OrderCancelResponse>> cancelStopOrders(@Query("symbol") String symbol);

    @GET("api/v1/orders/{orderId}")
    Call<KucoinFuturesResponse<OrderResponse>> getOrder(@Path("orderId") String orderId);

    @GET("api/v1/orders")
    Call<KucoinFuturesResponse<Pagination<OrderResponse>>> queryOrders(@Query("symbol") String symbol,
                                                               @Query("side") String side,
                                                               @Query("type") String type,
                                                               @Query("status") String status,
                                                               @Query("startAt") Long startAt,
                                                               @Query("endAt") Long endAt,
                                                               @Query("pageSize") int pageSize,
                                                               @Query("currentPage") int currentPage);

    @GET("api/v1/stopOrders")
    Call<KucoinFuturesResponse<Pagination<OrderResponse>>> queryStopOrders(@Query("symbol") String symbol,
                                                                   @Query("side") String side,
                                                                   @Query("type") String type,
                                                                   @Query("startAt") Long startAt,
                                                                   @Query("endAt") Long endAt,
                                                                   @Query("pageSize") int pageSize,
                                                                   @Query("currentPage") int currentPage);

    @GET("api/v1/recentDoneOrders")
    Call<KucoinFuturesResponse<List<OrderResponse>>> queryRecentDoneOrders();

}
