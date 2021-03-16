/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.WebsocketTokenResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/21
 */
public interface WebsocketAPI {

    /**
     * apply for a public token to create a websocket connection
     *
     * @return A new API token.
     */
    WebsocketTokenResponse getToken() throws IOException;

}
