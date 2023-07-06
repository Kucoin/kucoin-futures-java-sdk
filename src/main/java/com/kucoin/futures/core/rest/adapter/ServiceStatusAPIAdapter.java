/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.adapter;

import com.kucoin.futures.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.futures.core.rest.interfaces.ServiceStatusAPI;
import com.kucoin.futures.core.rest.interfaces.retrofit.ServiceStatusAPIRetrofit;
import com.kucoin.futures.core.rest.response.ServiceStatusResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @since 2020/6/29
 */
public class ServiceStatusAPIAdapter extends PublicRetrofitAPIImpl<ServiceStatusAPIRetrofit> implements ServiceStatusAPI {

    public ServiceStatusAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public ServiceStatusResponse getServiceStatus() throws IOException {
        return super.executeSync(getAPIImpl().getServiceStatus());
    }
}
