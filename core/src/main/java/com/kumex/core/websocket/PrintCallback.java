/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket;

import com.kumex.core.exception.KumexApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenshiwei on 2019/1/19.
 */
public class PrintCallback<T> implements KumexAPICallback<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCallback.class);

    @Override
    public void onResponse(T response) throws KumexApiException {
        LOGGER.debug("Got response: {}", response);
    }

}
