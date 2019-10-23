/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.FundingHistoryResponse;
import com.kumex.core.rest.response.HasMoreResponse;
import com.kumex.core.rest.response.KumexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/14
 */
public interface FundingFeeAPIRetrofit {

    @GET("api/v1/funding-history")
    Call<KumexResponse<HasMoreResponse<FundingHistoryResponse>>> getFundingHistory(@Query("symbol") String symbol,
                                                                                   @Query("reverse") Boolean reverse,
                                                                                   @Query("forward") Boolean forward,
                                                                                   @Query("startAt") Long startAt,
                                                                                   @Query("endAt") Long endAt,
                                                                                   @Query("offset") Long offset,
                                                                                   @Query("maxCount") Long maxCount);

}
