/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core;

import com.kucoin.futures.core.constants.APIConstants;
import com.kucoin.futures.core.impl.KucoinFuturesPrivateWSClientImpl;
import com.kucoin.futures.core.impl.KucoinFuturesPublicWSClientImpl;
import com.kucoin.futures.core.impl.KucoinFuturesRestClientImpl;
import com.kucoin.futures.core.rest.adapter.AccountAPIAdapter;
import com.kucoin.futures.core.rest.adapter.DepositAPIAdapter;
import com.kucoin.futures.core.rest.adapter.FillAPIAdapter;
import com.kucoin.futures.core.rest.adapter.HistoryAPIAdapter;
import com.kucoin.futures.core.rest.adapter.IndexAPIAdapter;
import com.kucoin.futures.core.rest.adapter.KChartAPIAdapter;
import com.kucoin.futures.core.rest.adapter.OrderAPIAdapter;
import com.kucoin.futures.core.rest.adapter.OrderBookAPIAdapter;
import com.kucoin.futures.core.rest.adapter.PositionAPIAdapter;
import com.kucoin.futures.core.rest.adapter.ServiceStatusAPIAdapter;
import com.kucoin.futures.core.rest.adapter.SymbolAPIAdaptor;
import com.kucoin.futures.core.rest.adapter.TickerAPIAdaptor;
import com.kucoin.futures.core.rest.adapter.TimeAPIAdapter;
import com.kucoin.futures.core.rest.adapter.TransferAPIAdapter;
import com.kucoin.futures.core.rest.adapter.WithdrawalAPIAdapter;
import com.kucoin.futures.core.rest.interfaces.DepositAPI;
import com.kucoin.futures.core.rest.interfaces.FillAPI;
import com.kucoin.futures.core.rest.interfaces.FundingFeeAPI;
import com.kucoin.futures.core.rest.interfaces.HistoryAPI;
import com.kucoin.futures.core.rest.interfaces.IndexAPI;
import com.kucoin.futures.core.rest.interfaces.KChartAPI;
import com.kucoin.futures.core.rest.interfaces.OrderAPI;
import com.kucoin.futures.core.rest.interfaces.OrderBookAPI;
import com.kucoin.futures.core.rest.interfaces.PositionAPI;
import com.kucoin.futures.core.rest.interfaces.ServiceStatusAPI;
import com.kucoin.futures.core.rest.interfaces.SymbolAPI;
import com.kucoin.futures.core.rest.interfaces.TickerAPI;
import com.kucoin.futures.core.rest.interfaces.TimeAPI;
import com.kucoin.futures.core.rest.interfaces.TransferAPI;
import com.kucoin.futures.core.rest.interfaces.WithdrawalAPI;
import com.kucoin.futures.core.websocket.ChooseServerStrategy;
import com.kucoin.futures.core.websocket.RandomChooseStrategy;
import com.kucoin.futures.core.rest.adapter.FundingFeeAPIAdapter;
import com.kucoin.futures.core.rest.interfaces.AccountAPI;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/9.
 */
@Getter
public class KucoinFuturesClientBuilder {

    private String apiKey;

    private String secret;

    private String passPhrase;

    private String baseUrl;

    private AccountAPI accountAPI;

    private DepositAPI depositAPI;

    private WithdrawalAPI withdrawalAPI;

    private TransferAPI transferAPI;

    private OrderAPI orderAPI;

    private FillAPI fillAPI;

    private PositionAPI positionAPI;

    private FundingFeeAPI fundingFeeAPI;

    private SymbolAPI symbolAPI;

    private TickerAPI tickerAPI;

    private OrderBookAPI orderBookAPI;

    private HistoryAPI historyAPI;

    private IndexAPI indexAPI;

    private TimeAPI timeAPI;

    private ServiceStatusAPI serviceStatusAPI;

    private KChartAPI kChartAPI;

    private ChooseServerStrategy chooseServerStrategy;

    public KucoinFuturesRestClient buildRestClient() {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (accountAPI == null) accountAPI = new AccountAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (depositAPI == null) depositAPI = new DepositAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (withdrawalAPI == null) withdrawalAPI = new WithdrawalAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (transferAPI == null) transferAPI = new TransferAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (fillAPI == null) fillAPI = new FillAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (positionAPI == null) positionAPI = new PositionAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (fundingFeeAPI == null) fundingFeeAPI = new FundingFeeAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (orderAPI == null) orderAPI = new OrderAPIAdapter(baseUrl, apiKey, secret, passPhrase);
        if (symbolAPI == null) symbolAPI = new SymbolAPIAdaptor(baseUrl);
        if (tickerAPI == null) tickerAPI = new TickerAPIAdaptor(baseUrl);
        if (orderBookAPI == null) orderBookAPI = new OrderBookAPIAdapter(baseUrl);
        if (historyAPI == null) historyAPI = new HistoryAPIAdapter(baseUrl);
        if (indexAPI == null) indexAPI = new IndexAPIAdapter(baseUrl);
        if (timeAPI == null) timeAPI = new TimeAPIAdapter(baseUrl);
        if (serviceStatusAPI == null) serviceStatusAPI = new ServiceStatusAPIAdapter(baseUrl);
        if (kChartAPI == null) kChartAPI = new KChartAPIAdapter(baseUrl);
        return new KucoinFuturesRestClientImpl(this);
    }

    public KucoinFuturesPublicWSClient buildPublicWSClient() throws IOException {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (chooseServerStrategy == null) chooseServerStrategy = new RandomChooseStrategy();
        KucoinFuturesPublicWSClientImpl client = new KucoinFuturesPublicWSClientImpl(this);
        return client;
    }

    public KucoinFuturesPrivateWSClient buildPrivateWSClient() throws IOException {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (chooseServerStrategy == null) chooseServerStrategy = new RandomChooseStrategy();
        KucoinFuturesPrivateWSClientImpl client = new KucoinFuturesPrivateWSClientImpl(this);
        return client;
    }

    public KucoinFuturesClientBuilder withApiKey(String apiKey, String secret, String passPhrase) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        return this;
    }

    public KucoinFuturesClientBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public KucoinFuturesClientBuilder withDepositAPI(DepositAPI depositAPI) {
        this.depositAPI = depositAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withTransferAPI(TransferAPI transferAPI) {
        this.transferAPI = transferAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withFillAPI(FillAPI fillAPI) {
        this.fillAPI = fillAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withOrderAPI(OrderAPI orderAPI) {
        this.orderAPI = orderAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withWithdrawalAPI(WithdrawalAPI withdrawalAPI) {
        this.withdrawalAPI = withdrawalAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withAccountAPI(AccountAPI accountAPI) {
        this.accountAPI = accountAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withSymbolAPI(SymbolAPI symbolAPI) {
        this.symbolAPI = symbolAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withOrderBookAPI(OrderBookAPI orderBookAPI) {
        this.orderBookAPI = orderBookAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withHistoryAPI(HistoryAPI historyAPI) {
        this.historyAPI = historyAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withPositionAPI(PositionAPI positionAPI) {
        this.positionAPI = positionAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withTimeAPI(TimeAPI timeAPI) {
        this.timeAPI = timeAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withFundingFeeAPI(FundingFeeAPI fundingFeeAPI) {
        this.fundingFeeAPI = fundingFeeAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withIndexAPI(IndexAPI indexAPI) {
        this.indexAPI = indexAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withTickerAPI(TickerAPI tickerAPI) {
        this.tickerAPI = tickerAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withServiceStatusAPI(ServiceStatusAPI serviceStatusAPI) {
        this.serviceStatusAPI = serviceStatusAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withKChartAPI(KChartAPI kChartAPI) {
        this.kChartAPI = kChartAPI;
        return this;
    }

    public KucoinFuturesClientBuilder withChooseServerStrategy(ChooseServerStrategy chooseServerStrategy) {
        this.chooseServerStrategy = chooseServerStrategy;
        return this;
    }
}
