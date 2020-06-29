/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core.rest.adapter;

import com.kumex.core.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kumex.core.rest.interfaces.KChartAPI;
import com.kumex.core.rest.interfaces.retrofit.KChartAPIRetrofit;

import java.io.IOException;
import java.util.List;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/29
 */
public class KChartAPIAdapter extends PublicRetrofitAPIImpl<KChartAPIRetrofit> implements KChartAPI {

    public KChartAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<List<String>> getKChart(String symbol, int granularity, Long from, Long to) throws IOException {
        return super.executeSync(getAPIImpl().getKChart(symbol, granularity, from, to));
    }
}
