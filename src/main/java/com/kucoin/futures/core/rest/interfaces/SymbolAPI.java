/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.ContractResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/11.
 */
public interface SymbolAPI {

    /**
     * Submit request to get the info of all open contracts.
     *
     * @return
     */
    List<ContractResponse> getOpenContractList() throws IOException;

    /**
     * Submit request to get info of the specified contract.
     *
     * @param symbol Symbol of the contract
     * @return
     */
    ContractResponse getContract(String symbol) throws IOException;

}
