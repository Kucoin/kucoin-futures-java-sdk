/*
 * Copyright 2019 Mek Global Limited
 */
package com.kumex.core;

import com.kumex.core.constants.APIConstants;
import com.kumex.core.impl.KumexPrivateWSClientImpl;
import com.kumex.core.impl.KumexPublicWSClientImpl;
import com.kumex.core.impl.KumexRestClientImpl;
import com.kumex.core.rest.adapter.AccountAPIAdapter;
import com.kumex.core.rest.adapter.DepositAPIAdapter;
import com.kumex.core.rest.adapter.FillAPIAdapter;
import com.kumex.core.rest.adapter.FundingFeeAPIAdapter;
import com.kumex.core.rest.adapter.HistoryAPIAdapter;
import com.kumex.core.rest.adapter.IndexAPIAdapter;
import com.kumex.core.rest.adapter.OrderAPIAdapter;
import com.kumex.core.rest.adapter.OrderBookAPIAdapter;
import com.kumex.core.rest.adapter.PositionAPIAdapter;
import com.kumex.core.rest.adapter.SymbolAPIAdaptor;
import com.kumex.core.rest.adapter.TickerAPIAdaptor;
import com.kumex.core.rest.adapter.TimeAPIAdapter;
import com.kumex.core.rest.adapter.TransferAPIAdapter;
import com.kumex.core.rest.adapter.WithdrawalAPIAdapter;
import com.kumex.core.rest.interfaces.AccountAPI;
import com.kumex.core.rest.interfaces.DepositAPI;
import com.kumex.core.rest.interfaces.FillAPI;
import com.kumex.core.rest.interfaces.FundingFeeAPI;
import com.kumex.core.rest.interfaces.HistoryAPI;
import com.kumex.core.rest.interfaces.IndexAPI;
import com.kumex.core.rest.interfaces.OrderAPI;
import com.kumex.core.rest.interfaces.OrderBookAPI;
import com.kumex.core.rest.interfaces.PositionAPI;
import com.kumex.core.rest.interfaces.SymbolAPI;
import com.kumex.core.rest.interfaces.TickerAPI;
import com.kumex.core.rest.interfaces.TimeAPI;
import com.kumex.core.rest.interfaces.TransferAPI;
import com.kumex.core.rest.interfaces.WithdrawalAPI;
import com.kumex.core.websocket.ChooseServerStrategy;
import com.kumex.core.websocket.RandomChooseStrategy;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/9.
 */
@Getter
public class KumexClientBuilder {

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

    private ChooseServerStrategy chooseServerStrategy;

    public KumexRestClient buildRestClient() {
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
        return new KumexRestClientImpl(this);
    }

    public KumexPublicWSClient buildPublicWSClient() throws IOException {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (chooseServerStrategy == null) chooseServerStrategy = new RandomChooseStrategy();
        KumexPublicWSClientImpl client = new KumexPublicWSClientImpl(this);
        return client;
    }

    public KumexPrivateWSClient buildPrivateWSClient() throws IOException {
        if (StringUtils.isBlank(baseUrl)) baseUrl = APIConstants.API_BASE_URL;
        if (chooseServerStrategy == null) chooseServerStrategy = new RandomChooseStrategy();
        KumexPrivateWSClientImpl client = new KumexPrivateWSClientImpl(this);
        return client;
    }

    public KumexClientBuilder withApiKey(String apiKey, String secret, String passPhrase) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        return this;
    }

    public KumexClientBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public KumexClientBuilder withDepositAPI(DepositAPI depositAPI) {
        this.depositAPI = depositAPI;
        return this;
    }

    public KumexClientBuilder withTransferAPI(TransferAPI transferAPI) {
        this.transferAPI = transferAPI;
        return this;
    }

    public KumexClientBuilder withFillAPI(FillAPI fillAPI) {
        this.fillAPI = fillAPI;
        return this;
    }

    public KumexClientBuilder withOrderAPI(OrderAPI orderAPI) {
        this.orderAPI = orderAPI;
        return this;
    }

    public KumexClientBuilder withWithdrawalAPI(WithdrawalAPI withdrawalAPI) {
        this.withdrawalAPI = withdrawalAPI;
        return this;
    }

    public KumexClientBuilder withAccountAPI(AccountAPI accountAPI) {
        this.accountAPI = accountAPI;
        return this;
    }

    public KumexClientBuilder withSymbolAPI(SymbolAPI symbolAPI) {
        this.symbolAPI = symbolAPI;
        return this;
    }

    public KumexClientBuilder withOrderBookAPI(OrderBookAPI orderBookAPI) {
        this.orderBookAPI = orderBookAPI;
        return this;
    }

    public KumexClientBuilder withHistoryAPI(HistoryAPI historyAPI) {
        this.historyAPI = historyAPI;
        return this;
    }

    public KumexClientBuilder withPositionAPI(PositionAPI positionAPI) {
        this.positionAPI = positionAPI;
        return this;
    }

    public KumexClientBuilder withTimeAPI(TimeAPI timeAPI) {
        this.timeAPI = timeAPI;
        return this;
    }

    public KumexClientBuilder withFundingFeeAPI(FundingFeeAPI fundingFeeAPI) {
        this.fundingFeeAPI = fundingFeeAPI;
        return this;
    }

    public KumexClientBuilder withIndexAPI(IndexAPI indexAPI) {
        this.indexAPI = indexAPI;
        return this;
    }

    public KumexClientBuilder withTickerAPI(TickerAPI tickerAPI) {
        this.tickerAPI = tickerAPI;
        return this;
    }

    public KumexClientBuilder withChooseServerStrategy(ChooseServerStrategy chooseServerStrategy) {
        this.chooseServerStrategy = chooseServerStrategy;
        return this;
    }
}
