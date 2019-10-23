/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.request.OrderCreateApiRequest;
import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.OrderCancelResponse;
import com.kumex.core.rest.response.OrderCreateResponse;
import com.kumex.core.rest.response.OrderResponse;
import com.kumex.core.rest.response.Pagination;
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
    Call<KumexResponse<OrderCreateResponse>> createOrder(@Body OrderCreateApiRequest opsRequest);

    @DELETE("api/v1/orders/{orderId}")
    Call<KumexResponse<OrderCancelResponse>> cancelOrder(@Path("orderId") String orderId);

    @DELETE("api/v1/orders")
    Call<KumexResponse<OrderCancelResponse>> cancelLimitOrders(@Query("symbol") String symbol);

    @DELETE("api/v1/stopOrders")
    Call<KumexResponse<OrderCancelResponse>> cancelStopOrders(@Query("symbol") String symbol);

    @GET("api/v1/orders/{orderId}")
    Call<KumexResponse<OrderResponse>> getOrder(@Path("orderId") String orderId);

    @GET("api/v1/orders")
    Call<KumexResponse<Pagination<OrderResponse>>> queryOrders(@Query("symbol") String symbol,
                                                               @Query("side") String side,
                                                               @Query("type") String type,
                                                               @Query("status") String status,
                                                               @Query("startAt") Long startAt,
                                                               @Query("endAt") Long endAt,
                                                               @Query("pageSize") int pageSize,
                                                               @Query("currentPage") int currentPage);

    @GET("api/v1/stopOrders")
    Call<KumexResponse<Pagination<OrderResponse>>> queryStopOrders(@Query("symbol") String symbol,
                                                                   @Query("side") String side,
                                                                   @Query("type") String type,
                                                                   @Query("startAt") Long startAt,
                                                                   @Query("endAt") Long endAt,
                                                                   @Query("pageSize") int pageSize,
                                                                   @Query("currentPage") int currentPage);

    @GET("api/v1/recentDoneOrders")
    Call<KumexResponse<List<OrderResponse>>> queryRecentDoneOrders();

}
