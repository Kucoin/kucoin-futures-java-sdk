/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.impl;

import com.kucoin.futures.core.rest.interfaces.*;
import com.kucoin.futures.core.KucoinFuturesClientBuilder;
import com.kucoin.futures.core.KucoinFuturesRestClient;

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

    private RiskLimitAPI riskLimitAPI;

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
        this.riskLimitAPI = kucoinBuilder.getRiskLimitAPI();
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

    @Override
    public RiskLimitAPI riskLimitAPI() {
        return riskLimitAPI;
    }
}
