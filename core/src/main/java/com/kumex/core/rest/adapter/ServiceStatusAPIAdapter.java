/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.ServiceStatusAPI;
import com.kumex.core.rest.interfaces.retrofit.ServiceStatusAPIRetrofit;
import com.kumex.core.rest.response.ServiceStatusResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/29
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
