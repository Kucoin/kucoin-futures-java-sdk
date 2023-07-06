package com.kucoin.futures.core.rest.interfaces.retrofit;

import com.kucoin.futures.core.rest.request.RiskLimitLevelUpdateRequest;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.rest.response.RiskLimitResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
public interface RiskLimitAPIRetrofit {
    @GET("api/v1/contracts/risk-limit/{symbol}")
    Call<KucoinFuturesResponse<List<RiskLimitResponse>>> getRiskLimit(@Path("symbol") String symbol);

    @POST("api/v1/position/risk-limit-level/change")
    Call<KucoinFuturesResponse<Boolean>> changeRiskLimit(@Body RiskLimitLevelUpdateRequest request);
}
