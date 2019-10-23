/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces;

import com.kumex.core.rest.response.TransactionHistoryResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface HistoryAPI {

    /**
     * List the last 100 trades for a symbol.
     *
     * @param symbol Symbol of the contract
     * @return
     */
    List<TransactionHistoryResponse> getTransactionHistories(String symbol) throws IOException;

}
