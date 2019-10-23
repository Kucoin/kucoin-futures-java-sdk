/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.adapter;

import java.io.IOException;

import com.kumex.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.TimeAPI;
import com.kumex.core.rest.interfaces.retrofit.TimeAPIRetrofit;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class TimeAPIAdapter extends PublicRetrofitAPIImpl<TimeAPIRetrofit> implements TimeAPI {

    public TimeAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Long getServerTimeStamp() throws IOException {
        return super.executeSync(getAPIImpl().getServerTimeStamp());
    }
}
