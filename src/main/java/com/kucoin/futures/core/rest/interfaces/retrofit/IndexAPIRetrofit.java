/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author chenshiwei
 * @since 2019/10/15
 */
public interface IndexAPIRetrofit {

    @GET("api/v1/interest/query")
    Call<KucoinFuturesResponse<HasMoreResponse<InterestRateResponse>>> getInterestRateList(@Query("symbol") String symbol,
                                                                                   @Query("reverse") Boolean reverse,
                                                                                   @Query("forward") Boolean forward,
                                                                                   @Query("starAt") Long starAt,
                                                                                   @Query("endAt") Long endAt,
                                                                                   @Query("offset") long offset,
                                                                                   @Query("maxCount") long maxCount);

    @GET("api/v1/index/query")
    Call<KucoinFuturesResponse<HasMoreResponse<IndexResponse>>> getIndexList(@Query("symbol") String symbol,
                                                                     @Query("reverse") Boolean reverse,
                                                                     @Query("forward") Boolean forward,
                                                                     @Query("starAt") Long starAt,
                                                                     @Query("endAt") Long endAt,
                                                                     @Query("offset") long offset,
                                                                     @Query("maxCount") long maxCount);

    @GET("api/v1/mark-price/{symbol}/current")
    Call<KucoinFuturesResponse<MarkPriceResponse>> getCurrentMarkPrice(@Path("symbol") String symbol);

    @GET("api/v1/premium/query")
    Call<KucoinFuturesResponse<HasMoreResponse<IndexRateResponse>>> getPremiumIndex(@Query("symbol") String symbol);

    @GET("api/v1/funding-rate/{symbol}/current")
    Call<KucoinFuturesResponse<FundingRateResponse>> getCurrentFundingRate(@Path("symbol") String symbol);

    @GET("api/v1/trade-statistics")
    Call<KucoinFuturesResponse<TradeStatisticsResponse>> getTradeStatistics();

}
