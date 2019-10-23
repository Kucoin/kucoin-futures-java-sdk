/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.interfaces;

import com.kumex.core.rest.response.TickerResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/14
 */
public interface TickerAPI {

    /**
     * The real-time ticker includes the last traded price, the last traded size, transaction ID,
     * the side of liquidity taker, the best bid price and size, the best ask price and size as well as the transaction time of the orders.
     * These messages can also be obtained through Websocket.
     * The Sequence Number is used to judge whether the messages pushed by Websocket is continuous.
     *
     * @param symbol Symbol of the contract
     * @return
     * @throws IOException
     */
    TickerResponse getTicker(String symbol) throws IOException;

}
