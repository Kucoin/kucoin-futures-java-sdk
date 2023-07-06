package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.RiskLimitResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
public interface RiskLimitAPI {
    /**
     * This interface can be used to obtain information about risk limit level of a specific contract
     *
     * @param symbol Symbol of the contract
     * @return
     * @throws IOException
     */
    List<RiskLimitResponse> getRiskLimit(String symbol) throws IOException;

    /**
     * This interface is for the adjustment of the risk limit level.
     * To adjust the level will cancel the open order, the response can only indicate whether the submit of the adjustment request is successful or not.
     *
     * @param symbol Symbol of the contract
     * @param level level
     * @return
     * @throws IOException
     */
    Boolean changeRiskLimit(String symbol, Integer level) throws IOException;
}
