/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.TransferAPI;
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
 * @email casocroz@gmail.com
 * @date 2019/8/14
 */
public class TransferAPIAdapter extends AuthRetrofitAPIImpl<TransferAPIRetrofit> implements TransferAPI {

    public TransferAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public TransferResponse toKucoinMainAccount(String bizNo, BigDecimal amount) throws IOException {
        return super.executeSync(getAPIImpl().applyTransfer(bizNo, amount));
    }

    @Override
    public TransferResponse toKucoinMainAccount(String bizNo, BigDecimal amount, String currency) throws IOException {
        return super.executeSync(getAPIImpl().applyTransfer(bizNo, amount, currency));
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
}
