/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces.retrofit;

import com.kumex.core.rest.response.KumexResponse;
import com.kumex.core.rest.response.Pagination;
import com.kumex.core.rest.response.TransferHistory;
import com.kumex.core.rest.response.TransferResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.math.BigDecimal;

/**
 * Transfer API retrofit interface
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/8/14
 */
public interface TransferAPIRetrofit {

    @POST("api/v1/transfer-out")
    Call<KumexResponse<TransferResponse>> applyTransfer(@Query("bizNo") String bizNo, @Query("amount") BigDecimal amount);

    @POST("api/v2/transfer-out")
    Call<KumexResponse<TransferResponse>> applyTransfer(@Query("bizNo") String bizNo, @Query("amount") BigDecimal amount,
                                                        @Query("currency") String currency);

    @GET("api/v1/transfer-list")
    Call<KumexResponse<Pagination<TransferHistory>>> getTransferOutRecords(@Query("currentPage") int currentPage,
                                                                          @Query("pageSize") int pageSize,
                                                                          @Query("status") String status,
                                                                          @Query("currency") String currency,
                                                                          @Query("startAt") Long startAt,
                                                                          @Query("endAt") Long endAt);

    @DELETE("api/v1/cancel/transfer-out")
    Call<KumexResponse<TransferResponse>> cancelTransfer(@Query("applyId") String applyId);

}
