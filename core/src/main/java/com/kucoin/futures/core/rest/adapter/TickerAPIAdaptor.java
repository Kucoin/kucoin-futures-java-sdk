/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.interfaces.TickerAPI;
import com.kucoin.futures.core.rest.interfaces.retrofit.TickerAPIRetrofit;
import com.kucoin.futures.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.futures.core.rest.response.TickerResponse;

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
