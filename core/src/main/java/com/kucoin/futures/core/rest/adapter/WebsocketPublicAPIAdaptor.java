/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.interfaces.WebsocketAPI;
import com.kucoin.futures.core.rest.interfaces.retrofit.WebsocketPublicAPIRetrofit;
import com.kucoin.futures.core.rest.response.WebsocketTokenResponse;
import com.kucoin.futures.core.rest.impl.retrofit.PublicRetrofitAPIImpl;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class WebsocketPublicAPIAdaptor extends PublicRetrofitAPIImpl<WebsocketPublicAPIRetrofit>
        implements WebsocketAPI {

    public WebsocketPublicAPIAdaptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public WebsocketTokenResponse getToken() throws IOException {
        return super.executeSync(getAPIImpl().getPublicToken());
    }
}
