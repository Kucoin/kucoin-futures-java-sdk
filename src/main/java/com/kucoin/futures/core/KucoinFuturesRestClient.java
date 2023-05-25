/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core;

import com.kucoin.futures.core.rest.interfaces.*;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface KucoinFuturesRestClient {

    AccountAPI accountAPI();

    DepositAPI depositAPI();

    WithdrawalAPI withdrawalAPI();

    TransferAPI transferAPI();

    OrderAPI orderAPI();

    FillAPI fillAPI();

    PositionAPI positionAPI();

    FundingFeeAPI fundingFeeAPI();

    SymbolAPI symbolAPI();

    TickerAPI tickerAPI();

    OrderBookAPI orderBookAPI();

    HistoryAPI historyAPI();

    IndexAPI indexAPI();

    TimeAPI timeAPI();

    ServiceStatusAPI serviceStatusAPI();

    KChartAPI kChartAPI();

    RiskLimitAPI riskLimitAPI();

}
