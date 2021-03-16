/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.response.DepositAddressResponse;
import com.kucoin.futures.core.rest.response.DepositResponse;
import com.kucoin.futures.core.rest.response.Pagination;

import java.io.IOException;

/**
 * Deposit API
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/7/25
 */
public interface DepositAPI {

    /**
     * Get deposit address of currency for deposit.
     * If return data is null , you may need create a deposit address first.
     *
     * @param currency the code of the currency
     * @return Details of a deposit address.
     */
    DepositAddressResponse getDepositAddress(String currency) throws IOException;

    /**
     * Get deposit page list.
     *
     * @param status      [optional] Status. Available value: PROCESSING, SUCCESS, and FAILURE
     * @param currency    [Optional] Currency, including XBT,USDT
     * @param request     [optional] include startAt endAt currentPage and pageSize parameters
     * @return A page of deposits.
     */
    Pagination<DepositResponse> getDepositList(String status, String currency, DuringPageRequest request) throws IOException;

}
