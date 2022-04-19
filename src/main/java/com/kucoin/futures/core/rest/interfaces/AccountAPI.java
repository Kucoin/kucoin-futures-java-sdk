/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.exception.KucoinFuturesApiException;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;
import com.kucoin.futures.core.rest.response.AccountOverviewResponse;
import com.kucoin.futures.core.rest.response.HasMoreResponse;
import com.kucoin.futures.core.rest.response.TransactionHistory;

import java.io.IOException;

/**
 * Account API
 *
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/7/25
 */
public interface AccountAPI {

    /**
     * User's account overview
     * <p>
     * This endpoint requires the General permission.
     *
     * @param currency [Optional] Currecny ,including XBT,USDT,Default XBT
     * @return The accounts.
     * @throws IOException               on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    AccountOverviewResponse accountOverview(String currency) throws IOException;

    /**
     * If there are open positions, the status of the first page returned will be Pending,
     * indicating the realised profit and loss in the current 8-hour settlement period.
     * Please specify the minimum offset number of the current page into the offset field to turn the page.
     *
     * @param type     [Optional] Type RealisedPNL-Realised profit and loss, Deposit-Deposit, Withdrawal-withdraw,
     *                 Transferin-Transfer in, TransferOut-Transfer out
     * @param currency [Optional] Currency of transaction history XBT or USDT
     * @param forward  [optional] This parameter functions to judge whether the lookup is forward or not.
     *                 True means “yes” and False means “no”. This parameter is set as true by default
     * @param request  [Optional] include startAt endAt offset and maxCount optional parameters
     * @return The account balance.
     * @throws IOException               on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    HasMoreResponse<TransactionHistory> transactionHistory(String type, String currency, Boolean forward, DuringHasMoreRequest request)
            throws IOException;

}
