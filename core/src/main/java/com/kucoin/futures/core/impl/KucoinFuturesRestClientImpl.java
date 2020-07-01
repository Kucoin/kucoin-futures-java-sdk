/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.impl;

import com.kucoin.futures.core.rest.interfaces.DepositAPI;
import com.kucoin.futures.core.rest.interfaces.FillAPI;
import com.kucoin.futures.core.rest.interfaces.IndexAPI;
import com.kucoin.futures.core.rest.interfaces.KChartAPI;
import com.kucoin.futures.core.rest.interfaces.OrderAPI;
import com.kucoin.futures.core.rest.interfaces.PositionAPI;
import com.kucoin.futures.core.rest.interfaces.ServiceStatusAPI;
import com.kucoin.futures.core.rest.interfaces.SymbolAPI;
import com.kucoin.futures.core.rest.interfaces.TickerAPI;
import com.kucoin.futures.core.rest.interfaces.WithdrawalAPI;
import com.kucoin.futures.core.KucoinFuturesClientBuilder;
import com.kucoin.futures.core.KucoinFuturesRestClient;
import com.kucoin.futures.core.rest.interfaces.AccountAPI;
import com.kucoin.futures.core.rest.interfaces.FundingFeeAPI;
import com.kucoin.futures.core.rest.interfaces.HistoryAPI;
import com.kucoin.futures.core.rest.interfaces.OrderBookAPI;
import com.kucoin.futures.core.rest.interfaces.TimeAPI;
import com.kucoin.futures.core.rest.interfaces.TransferAPI;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public class KucoinFuturesRestClientImpl implements KucoinFuturesRestClient {

    private AccountAPI accountAPI;

    private DepositAPI depositAPI;

    private WithdrawalAPI withdrawAPI;

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

    public KucoinFuturesRestClientImpl(KucoinFuturesClientBuilder kucoinBuilder) {
        this.accountAPI = kucoinBuilder.getAccountAPI();
        this.depositAPI = kucoinBuilder.getDepositAPI();
        this.withdrawAPI = kucoinBuilder.getWithdrawalAPI();
        this.transferAPI = kucoinBuilder.getTransferAPI();
        this.orderAPI = kucoinBuilder.getOrderAPI();
        this.fillAPI = kucoinBuilder.getFillAPI();
        this.positionAPI = kucoinBuilder.getPositionAPI();
        this.fundingFeeAPI = kucoinBuilder.getFundingFeeAPI();
        this.symbolAPI = kucoinBuilder.getSymbolAPI();
        this.tickerAPI = kucoinBuilder.getTickerAPI();
        this.orderBookAPI = kucoinBuilder.getOrderBookAPI();
        this.historyAPI = kucoinBuilder.getHistoryAPI();
        this.indexAPI = kucoinBuilder.getIndexAPI();
        this.timeAPI = kucoinBuilder.getTimeAPI();
        this.serviceStatusAPI = kucoinBuilder.getServiceStatusAPI();
        this.kChartAPI = kucoinBuilder.getKChartAPI();
    }

    @Override
    public AccountAPI accountAPI() {
        return accountAPI;
    }

    @Override
    public DepositAPI depositAPI() {
        return depositAPI;
    }

    @Override
    public FillAPI fillAPI() {
        return fillAPI;
    }

    @Override
    public PositionAPI positionAPI() {
        return positionAPI;
    }

    @Override
    public FundingFeeAPI fundingFeeAPI() {
        return fundingFeeAPI;
    }

    @Override
    public OrderAPI orderAPI() {
        return orderAPI;
    }

    @Override
    public WithdrawalAPI withdrawalAPI() {
        return withdrawAPI;
    }

    @Override
    public TransferAPI transferAPI() {
        return transferAPI;
    }

    @Override
    public SymbolAPI symbolAPI() {
        return symbolAPI;
    }

    @Override
    public TickerAPI tickerAPI() {
        return tickerAPI;
    }

    @Override
    public OrderBookAPI orderBookAPI() {
        return orderBookAPI;
    }

    @Override
    public HistoryAPI historyAPI() {
        return historyAPI;
    }

    @Override
    public IndexAPI indexAPI() {
        return indexAPI;
    }

    @Override
    public TimeAPI timeAPI() {
        return timeAPI;
    }

    @Override
    public ServiceStatusAPI serviceStatusAPI() {
        return serviceStatusAPI;
    }

    @Override
    public KChartAPI kChartAPI() {
        return kChartAPI;
    }

}
