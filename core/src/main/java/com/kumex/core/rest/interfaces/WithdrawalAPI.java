/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces;

import com.kumex.core.rest.request.DuringPageRequest;
import com.kumex.core.rest.request.WithdrawApplyRequest;
import com.kumex.core.rest.response.Pagination;
import com.kumex.core.rest.response.WithdrawApplyResponse;
import com.kumex.core.rest.response.WithdrawQuotaResponse;
import com.kumex.core.rest.response.WithdrawResponse;

import java.io.IOException;

/**
 * Withdrawal API
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/7/25
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
     * @param request     [optional] include startAt endAt currentPage and pageSize parameters
     * @return A page of withdrawals.
     */
    Pagination<WithdrawResponse> getWithdrawList(String status, DuringPageRequest request) throws IOException;

}
