/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core;

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
import com.kucoin.futures.core.rest.interfaces.AccountAPI;

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

}
