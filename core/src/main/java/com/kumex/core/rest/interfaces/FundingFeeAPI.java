/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces;

import com.kumex.core.rest.request.DuringHasMoreRequest;
import com.kumex.core.rest.response.FundingHistoryResponse;
import com.kumex.core.rest.response.HasMoreResponse;

import java.io.IOException;

/**
 * Funding Fee API
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/14
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

}
