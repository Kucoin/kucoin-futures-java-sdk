/*
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.TickerAPI;
import com.kumex.core.rest.interfaces.retrofit.TickerAPIRetrofit;
import com.kumex.core.rest.response.TickerResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
public class TickerAPIAdaptor extends PublicRetrofitAPIImpl<TickerAPIRetrofit> implements TickerAPI {

    public TickerAPIAdaptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public TickerResponse getTicker(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getTicker(symbol));
    }
}
