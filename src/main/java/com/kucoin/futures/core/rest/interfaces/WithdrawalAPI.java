/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.WithdrawApplyResponse;
import com.kucoin.futures.core.rest.response.WithdrawQuotaResponse;
import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.request.WithdrawApplyRequest;
import com.kucoin.futures.core.rest.response.Pagination;
import com.kucoin.futures.core.rest.response.WithdrawResponse;

import java.io.IOException;

/**
 * Withdrawal API
 * @author chenshiwei
 * @since 2019/7/25
 */
public interface WithdrawalAPI {

    /**
     * get withdrawal quotas
     *
     * @param currency currency. e.g. BTC
     * @return The withdrawal quotas.
     */
    WithdrawQuotaResponse getWithdrawQuotas(String currency) throws IOException;

    /**
     * apply withdraw
     *
     * @param request
     * @return A response containing the withdrawal id.
     */
    WithdrawApplyResponse withdrawFunds(WithdrawApplyRequest request) throws IOException;

    /**
     * Only withdrawals in processing status could be cancelled.
     *
     * @param withdrawalId unique identity for withdrawal order
     */
    void cancelWithdraw(String withdrawalId) throws IOException;

    /**
     * get withdrawals list
     *
     * @param status      [optional] Status. Available value: PROCESSING, WALLET_PROCESSING, SUCCESS, and FAILURE
     * @param currency    [optional] Currency, including XBT,USDT
     *@param request      [optional] include startAt endAt currentPage and pageSize parameters  @return A page of withdrawals.
     */
    Pagination<WithdrawResponse> getWithdrawList(String status, String currency, DuringPageRequest request) throws IOException;

}
