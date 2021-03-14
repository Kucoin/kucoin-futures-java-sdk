/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.model.Level2Message;
import com.kucoin.futures.core.model.Level3Message;
import com.kucoin.futures.core.rest.response.OrderBookResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface OrderBookAPI {

    /**
     * Get a snapshot of aggregated open orders for a symbol.
     * Level 2 order book includes all bids and asks (aggregated by price).
     * This level returns only one aggregated size for each price (as if there was only one single order for that price).
     * This API will return data with full depth.
     * It is generally used by professional traders because it uses more server resources and traffic, and we have strict access frequency control.
     * To maintain up-to-date Order Book, please use Websocket incremental feed after retrieving the Level 2 snapshot.
     * In the returned data, the sell side is sorted low to high by price and the buy side is sorted high to low by price.
     *
     * @param symbol Symbol of the contract
     * @return The full level2 order book.
     */
    OrderBookResponse getFullLevel2OrderBook(String symbol) throws IOException;

    /**
     * Get a snapshot of aggregated open orders for a symbol.
     * This API will return data with 20 depth.
     * @param symbol
     * @return
     * @throws IOException
     */
    OrderBookResponse getDepth20Level2OrderBook(String symbol) throws IOException;

    /**
     * Get a snapshot of aggregated open orders for a symbol.
     * This API will return data with 100 depth.
     * @param symbol
     * @return
     * @throws IOException
     */
    OrderBookResponse getDepth100Level2OrderBook(String symbol) throws IOException;


    /**
     * If the messages pushed by Websocket is not continuous, you can submit the following request and re-pull the data to ensure that the sequence is not missing.
     * In the request, the start parameter is the sequence number of your last received message plus 1,
     * and the end parameter is the sequence number of your current received message minus 1.
     * After re-pulling the messages and applying them to your local exchange order book,
     * you can continue to update the order book via Websocket incremental feed.
     * If the difference between the end and start parameter is more than 500,
     * please stop using this request and we suggest you to rebuild the Level 2 orderbook.
     * Level 2 message pulling method: Take price as the key value and overwrite the local order quantity with the quantity in messages.
     * If the quantity of a certain price in the pushed message is 0, please delete the corresponding data of that price.
     *
     * @param symbol Symbol of the contract
     * @param start  Start sequence number (included in the returned data)
     * @param end    End sequence number (included in the returned data)
     * @return The part level2 order book.
     */
    @Deprecated
    List<Level2Message> getLevel2PullingMessages(String symbol, long start, long end) throws IOException;

    /**
     * Get a list of open orders for a symbol. Level-3 order book includes all bids and asks (non-aggregated, each item in Level-3 means a single order).
     * Level 3 is non-aggregated and returns the entire order book.
     * This API is generally used by professional traders because it uses more server resources and traffic, and we have strict access frequency control.
     * To Maintain up-to-date Order Book in real time, please use it with Websocket Feed.
     *
     * @param symbol Symbol of the contract
     * @return The full level3 order book.
     */
    OrderBookResponse getFullLevel3OrderBook(String symbol) throws IOException;

    /**
     *
     * @param symbol Symbol of the contract
     * @param start  Start sequence number (included in the returned data)
     * @param end    End sequence number (included in the returned data)
     * @return The part level3 order book.
     */
    @Deprecated
    List<Level3Message> getLevel3PullingMessages(String symbol, long start, long end) throws IOException;

}
