/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.response.PublicFundingReteResponse;
import com.kucoin.futures.core.rest.response.FundingHistoryResponse;
import com.kucoin.futures.core.rest.response.HasMoreResponse;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author chenshiwei
 * @since 2019/10/14
 */
public interface FundingFeeAPIRetrofit {

    @GET("api/v1/funding-history")
    Call<KucoinFuturesResponse<HasMoreResponse<FundingHistoryResponse>>> getFundingHistory(@Query("symbol") String symbol,
                                                                                   @Query("reverse") Boolean reverse,
                                                                                   @Query("forward") Boolean forward,
                                                                                   @Query("startAt") Long startAt,
                                                                                   @Query("endAt") Long endAt,
                                                                                   @Query("offset") Long offset,
                                                                                   @Query("maxCount") Long maxCount);

    @GET("/api/v1/contract/funding-rates")
    Call<KucoinFuturesResponse<List<PublicFundingReteResponse>>> getFundingRates(@Query("symbol") String symbol,
                                                                                @Query("from") long from,
                                                                                @Query("to") long to);

}
