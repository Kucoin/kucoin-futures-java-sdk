/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket;

import com.kumex.core.exception.KumexApiException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KumexAPICallback<T> {

    void onResponse(T response) throws KumexApiException;

}
