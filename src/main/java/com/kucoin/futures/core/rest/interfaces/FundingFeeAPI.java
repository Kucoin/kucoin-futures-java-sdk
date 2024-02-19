/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.PublicFundingReteResponse;
import com.kucoin.futures.core.rest.response.FundingHistoryResponse;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;
import com.kucoin.futures.core.rest.response.HasMoreResponse;

import java.io.IOException;
import java.util.List;

/**
 * Funding Fee API
 * @author chenshiwei
 * @since 2019/10/14
 */
public interface FundingFeeAPI {

    /**
     * Submit request to get the funding history.
     *
     * @param symbol  Symbol of the contract
     * @param reverse [optional] This parameter functions to judge whether the lookup is forward or not.
     *                True means “yes” and False means “no”. This parameter is set as true by default
     * @param forward [optional] This parameter functions to judge whether the lookup is forward or not.
     *                True means “yes” and False means “no”. This parameter is set as true by default
     * @param request [Optional] include startAt endAt offset and maxCount optional parameters
     * @return
     * @throws IOException
     */
    HasMoreResponse<FundingHistoryResponse> getFundingHistory(String symbol, Boolean reverse, Boolean forward,
                                                              DuringHasMoreRequest request) throws IOException;

    /**
     * Get Public Funding History
     *
     * @param symbol
     * @param from
     * @param to
     * @return
     * @throws IOException
     */
    List<PublicFundingReteResponse> getPublicFundingRates(String symbol, long from, long to) throws IOException;

}
