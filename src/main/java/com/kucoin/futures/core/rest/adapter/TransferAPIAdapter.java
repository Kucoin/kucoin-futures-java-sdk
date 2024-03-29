/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interceptor.FuturesApiKey;
import com.kucoin.futures.core.rest.interfaces.TransferAPI;
import com.kucoin.futures.core.rest.request.TransferApplyRequest;
import com.kucoin.futures.core.rest.request.TransferInRequest;
import com.kucoin.futures.core.rest.request.TransferOutRequest;
import com.kucoin.futures.core.rest.response.TransferHistory;
import com.kucoin.futures.core.rest.interfaces.retrofit.TransferAPIRetrofit;
import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.response.Pagination;
import com.kucoin.futures.core.rest.response.TransferResponse;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Transfer API adapter
 * @author chenshiwei
 * @since 2019/8/14
 */
public class TransferAPIAdapter extends AuthRetrofitAPIImpl<TransferAPIRetrofit> implements TransferAPI {

    public TransferAPIAdapter(String baseUrl, FuturesApiKey apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Override
    public TransferResponse toKucoinMainAccount(String bizNo, BigDecimal amount, String currency) throws IOException {
        TransferApplyRequest request = new TransferApplyRequest();
        request.setBizNo(bizNo);
        request.setAmount(amount);
        request.setCurrency(currency);
        return super.executeSync(getAPIImpl().applyTransfer(request));
    }

    @Override
    public Pagination<TransferHistory> getTransferOutRecords(String status, String currency, DuringPageRequest request) throws IOException {
        if (request == null) request = DuringPageRequest.builder().build();
        return super.executeSync(getAPIImpl().getTransferOutRecords(request.getCurrentPage(), request.getPageSize(), status,
                currency, request.getStarAt(), request.getEndAt()));
    }

    @Override
    public void cancelTransferOutRequest(String applyId) throws IOException {
        super.executeSync(getAPIImpl().cancelTransfer(applyId));
    }

    @Override
    public TransferResponse transferOut(String recAccountType, BigDecimal amount, String currency) throws IOException {
        TransferOutRequest transferOutRequest = new TransferOutRequest();
        transferOutRequest.setRecAccountType(recAccountType);
        transferOutRequest.setAmount(amount);
        transferOutRequest.setCurrency(currency);
        return super.executeSync(getAPIImpl().transferOut(transferOutRequest));
    }

    @Override
    public void transferIn(String payAccountType, BigDecimal amount, String currency) throws IOException {
        TransferInRequest transferInRequest = new TransferInRequest();
        transferInRequest.setPayAccountType(payAccountType);
        transferInRequest.setAmount(amount);
        transferInRequest.setCurrency(currency);
        super.executeSync(getAPIImpl().transferIn(transferInRequest));
    }
}
