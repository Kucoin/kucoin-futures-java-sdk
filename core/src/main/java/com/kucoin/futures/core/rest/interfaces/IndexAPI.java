/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.IndexResponse;
import com.kucoin.futures.core.rest.response.MarkPriceResponse;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;
import com.kucoin.futures.core.rest.response.FundingRateResponse;
import com.kucoin.futures.core.rest.response.HasMoreResponse;
import com.kucoin.futures.core.rest.response.IndexRateResponse;
import com.kucoin.futures.core.rest.response.InterestRateResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
public interface IndexAPI {

    /**
     * Check interest rate list.
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
    HasMoreResponse<InterestRateResponse> getInterestRateList(String symbol, Boolean reverse, Boolean forward,
                                                              DuringHasMoreRequest request) throws IOException;

    /**
     * Check index list
     *
     * @param symbol  Symbol of the contract
     * @param reverse [optional] This parameter functions to judge whether the lookup is forward or not.
     *                True means “yes” and False means “no”. This parameter is set as true by default
     * @param forward [optional] This parameter functions to judge whether the lookup is forward or not.
     *                True means “yes” and False means “no”. This parameter is set as true by default
     * @param request [Optional] include startAt endAt offset and maxCount optional parameters
     * @return
     * @throws Exception
     */
    HasMoreResponse<IndexResponse> getIndexList(String symbol, Boolean reverse, Boolean forward,
                                                DuringHasMoreRequest request) throws IOException;

    /**
     * Check the current mark price
     *
     * @param symbol Symbol of the contract
     * @return
     */
    MarkPriceResponse getCurrentMarkPrice(String symbol) throws IOException;

    /**
     * Submit request to get premium index.
     *
     * @param symbol Symbol of the contract
     * @return
     */
    HasMoreResponse<IndexRateResponse> getPremiumIndex(String symbol) throws IOException;

    /**
     * Submit request to check the current mark price.
     *
     * @param symbol Symbol of the contract
     * @return
     */
    FundingRateResponse getCurrentFundingRate(String symbol) throws IOException;
}
