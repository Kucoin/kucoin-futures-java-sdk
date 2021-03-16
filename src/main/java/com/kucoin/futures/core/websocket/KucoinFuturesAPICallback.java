/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket;

import com.kucoin.futures.core.exception.KucoinFuturesApiException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KucoinFuturesAPICallback<T> {

    void onResponse(T response) throws KucoinFuturesApiException;

}
