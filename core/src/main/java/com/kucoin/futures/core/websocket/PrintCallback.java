/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket;

import com.kucoin.futures.core.exception.KucoinFuturesApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenshiwei on 2019/1/19.
 */
public class PrintCallback<T> implements KucoinFuturesAPICallback<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCallback.class);

    @Override
    public void onResponse(T response) throws KucoinFuturesApiException {
        LOGGER.debug("Got response: {}", response);
    }

}
