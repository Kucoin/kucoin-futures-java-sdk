/*
 * Copyright 2019 Mek Global Limited.
 */

package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.FundingRateResponse;
import com.kumex.core.rest.response.HasMoreResponse;
import com.kumex.core.rest.response.IndexRateResponse;
import com.kumex.core.rest.response.IndexResponse;
import com.kumex.core.rest.response.InterestRateResponse;
import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.MarkPriceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
public interface IndexAPIRetrofit {

    @GET("api/v1/interest/query")
    Call<KumexResponse<HasMoreResponse<InterestRateResponse>>> getInterestRateList(@Query("symbol") String symbol,
                                                                                   @Query("reverse") Boolean reverse,
                                                                                   @Query("forward") Boolean forward,
                                                                                   @Query("starAt") Long starAt,
                                                                                   @Query("endAt") Long endAt,
                                                                                   @Query("offset") long offset,
                                                                                   @Query("maxCount") long maxCount);

    @GET("api/v1/index/query")
    Call<KumexResponse<HasMoreResponse<IndexResponse>>> getIndexList(@Query("symbol") String symbol,
                                                                     @Query("reverse") Boolean reverse,
                                                                     @Query("forward") Boolean forward,
                                                                     @Query("starAt") Long starAt,
                                                                     @Query("endAt") Long endAt,
                                                                     @Query("offset") long offset,
                                                                     @Query("maxCount") long maxCount);

    @GET("api/v1/mark-price/{symbol}/current")
    Call<KumexResponse<MarkPriceResponse>> getCurrentMarkPrice(@Path("symbol") String symbol);

    @GET("api/v1/premium/query")
    Call<KumexResponse<HasMoreResponse<IndexRateResponse>>> getPremiumIndex(@Query("symbol") String symbol);

    @GET("v1/funding-rate/{symbol}/current")
    Call<KumexResponse<FundingRateResponse>> getCurrentFundingRate(@Path("symbol") String symbol);

}
