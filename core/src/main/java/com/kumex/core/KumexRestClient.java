/*
 * Copyright 2019 Mek Global Limited
 */
package com.kumex.core;

import com.kumex.core.rest.interfaces.AccountAPI;
import com.kumex.core.rest.interfaces.DepositAPI;
import com.kumex.core.rest.interfaces.FillAPI;
import com.kumex.core.rest.interfaces.FundingFeeAPI;
import com.kumex.core.rest.interfaces.HistoryAPI;
import com.kumex.core.rest.interfaces.IndexAPI;
import com.kumex.core.rest.interfaces.KChartAPI;
import com.kumex.core.rest.interfaces.OrderAPI;
import com.kumex.core.rest.interfaces.OrderBookAPI;
import com.kumex.core.rest.interfaces.PositionAPI;
import com.kumex.core.rest.interfaces.ServiceStatusAPI;
import com.kumex.core.rest.interfaces.SymbolAPI;
import com.kumex.core.rest.interfaces.TickerAPI;
import com.kumex.core.rest.interfaces.TimeAPI;
import com.kumex.core.rest.interfaces.TransferAPI;
import com.kumex.core.rest.interfaces.WithdrawalAPI;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface KumexRestClient {

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
