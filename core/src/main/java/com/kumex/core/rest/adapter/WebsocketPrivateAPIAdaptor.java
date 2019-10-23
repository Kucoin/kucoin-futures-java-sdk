/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.WebsocketAPI;
import com.kumex.core.rest.interfaces.retrofit.WebsocketAuthAPIRetrofit;
import com.kumex.core.rest.response.WebsocketTokenResponse;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class WebsocketPrivateAPIAdaptor extends AuthRetrofitAPIImpl<WebsocketAuthAPIRetrofit>
        implements WebsocketAPI {

    public WebsocketPrivateAPIAdaptor(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public WebsocketTokenResponse getToken() throws IOException {
        return super.executeSync(getAPIImpl().getPrivateToken());
    }
}
