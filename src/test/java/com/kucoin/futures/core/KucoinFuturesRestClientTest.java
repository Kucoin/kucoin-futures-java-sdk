/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core;

import com.kucoin.futures.core.exception.KucoinFuturesApiException;
import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.request.OrderCreateApiRequest;
import com.kucoin.futures.core.rest.request.WithdrawApplyRequest;
import com.kucoin.futures.core.rest.response.*;
import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by chenshiwei on 2019/1/21.
 */
public class KucoinFuturesRestClientTest extends BaseTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void accountAPI() throws Exception {
        AccountOverviewResponse accountOverviewResponse = futuresRestClient.accountAPI().accountOverview(null);
        assertThat(accountOverviewResponse, notNullValue());

        HasMoreResponse<TransactionHistory> transactionHistoryHasMoreResponse = futuresRestClient.accountAPI()
                .transactionHistory(null, null, null, null);
        assertThat(transactionHistoryHasMoreResponse, notNullValue());
        assertThat(transactionHistoryHasMoreResponse.isHasMore(), Is.is(false));
    }

    @Test
    @Ignore
    public void depositAPI() throws Exception {

        exception.expect(KucoinFuturesApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        futuresRestClient.depositAPI().getDepositAddress("XBT");

        exception.expect(KucoinFuturesApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        futuresRestClient.depositAPI().getDepositList("SUCCESS", null, pageRequest);
    }

    @Test
    @Ignore
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = futuresRestClient.withdrawalAPI()
                .getWithdrawList("FAILURE", null, null);
        assertThat(withdrawList, notNullValue());

        WithdrawQuotaResponse kcs = futuresRestClient.withdrawalAPI().getWithdrawQuotas("XBT");
        assertThat(kcs, notNullValue());

        exception.expect(KucoinFuturesApiException.class);
        exception.expectMessage("Sandbox environment cannot be withdrawn");
        WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address("123467")
                .amount(BigDecimal.valueOf(0.00000001)).currency("XBT").build();
        futuresRestClient.withdrawalAPI().withdrawFunds(withdrawApplyRequest);

        futuresRestClient.withdrawalAPI().cancelWithdraw("1234567");
    }

    @Test
    public void transferAPI() throws Exception {
        DuringPageRequest pageRequest = DuringPageRequest.builder()
                .starAt(LocalDateTime.now().minusHours(23).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .endAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .currentPage(1)
                .pageSize(10).build();

        Pagination<TransferHistory> records = futuresRestClient.transferAPI().getTransferOutRecords("SUCCESS", null, pageRequest);
        assertThat(records.getItems().size(), is(0));

        TransferResponse transferResponse = futuresRestClient.transferAPI().toKucoinMainAccount("123456", BigDecimal.valueOf(0.0000001), "USDT");
        assertThat(transferResponse.getApplyId(), notNullValue());

        futuresRestClient.transferAPI().cancelTransferOutRequest(transferResponse.getApplyId());
    }

    @Test
    public void orderAPI() throws Exception {
        OrderCreateResponse order = placeCannotDealLimitOrder();
        assertThat(order, notNullValue());

        OrderResponse orderDetail = futuresRestClient.orderAPI().getOrderDetail(order.getOrderId());
        assertThat(orderDetail, notNullValue());

        OrderCancelResponse orderCancelResponse = futuresRestClient.orderAPI().cancelOrder(order.getOrderId());
        assertThat(orderCancelResponse.getCancelledOrderIds().size(), is(1));

        placeCannotDealLimitOrder();
        OrderCancelResponse cancelAllLimitOrders = futuresRestClient.orderAPI().cancelAllLimitOrders(SYMBOL);
        assertThat(cancelAllLimitOrders.getCancelledOrderIds().size(), greaterThan(0));

        placeCannotTriggerStopOrder();
        Pagination<OrderResponse> stopOrderList = futuresRestClient.orderAPI().getUntriggeredStopOrderList(SYMBOL,
                "buy", "limit", pageRequest);
        assertThat(stopOrderList.getItems().size(), greaterThan(0));

        OrderCancelResponse cancelAllStopOrders = futuresRestClient.orderAPI().cancelAllStopOrders(SYMBOL);
        assertThat(cancelAllStopOrders.getCancelledOrderIds().size(), greaterThan(0));

        Pagination<OrderResponse> orderList = futuresRestClient.orderAPI().getOrderList(SYMBOL, "buy",
                "limit", "done", null);
        assertThat(orderList.getItems().size(), greaterThan(0));

        List<OrderResponse> recentDoneOrders = futuresRestClient.orderAPI().getRecentDoneOrders();
        assertThat(recentDoneOrders.size(), greaterThan(0));

    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<FillResponse> fills = futuresRestClient.fillAPI().getFills(null, null, null,
                null, null);
        assertThat(fills, notNullValue());

        List<FillResponse> fillResponses = futuresRestClient.fillAPI().recentFills();
        assertThat(fillResponses, notNullValue());

        ActiveOrderResponse xbtusdm = futuresRestClient.fillAPI().calActiveOrderValue(SYMBOL);
        assertThat(xbtusdm, notNullValue());
    }

    @Test
    public void positionAPI() throws Exception {
        PositionResponse position = futuresRestClient.positionAPI().getPosition(SYMBOL);
        assertThat(position, notNullValue());

        List<PositionResponse> positions = futuresRestClient.positionAPI().getPositions();
        assertThat(positions, notNullValue());

        if (!positions.isEmpty()) {
            futuresRestClient.positionAPI().setAutoDepositMargin(SYMBOL, true);
            futuresRestClient.positionAPI().addMarginManually(SYMBOL, BigDecimal.valueOf(0.000001), "123456");
        }

    }

    @Test
    public void fundingFeeAPI() throws Exception {
        HasMoreResponse<FundingHistoryResponse> fundingHistory = futuresRestClient.fundingFeeAPI()
                .getFundingHistory(SYMBOL, null, null, hasMoreRequest);
        assertThat(fundingHistory, notNullValue());
    }

    @Test
    public void symbolAPI() throws Exception {
        ContractResponse contract = futuresRestClient.symbolAPI().getContract(SYMBOL);
        assertThat(contract, notNullValue());

        List<ContractResponse> openContractList = futuresRestClient.symbolAPI().getOpenContractList();
        assertThat(openContractList.size(), greaterThan(0));
    }

    @Test
    public void tickerAPI() throws Exception {
        TickerResponse ticker = futuresRestClient.tickerAPI().getTicker(SYMBOL);
        assertThat(ticker, notNullValue());
    }

    @Test
    public void orderBookAPI() throws Exception {
        OrderBookResponse fullOrderBookAggregated = futuresRestClient.orderBookAPI().getFullLevel2OrderBook(SYMBOL);
        assertThat(fullOrderBookAggregated, notNullValue());

        OrderBookResponse orderBook20 = futuresRestClient.orderBookAPI().getDepth20Level2OrderBook(SYMBOL);
        assertThat(orderBook20, notNullValue());

        OrderBookResponse orderBook100 = futuresRestClient.orderBookAPI().getDepth100Level2OrderBook(SYMBOL);
        assertThat(orderBook100, notNullValue());

        OrderBookResponse fullOrderBookAtomic = futuresRestClient.orderBookAPI().getFullLevel3OrderBook(SYMBOL);
        assertThat(fullOrderBookAtomic, notNullValue());
    }

    @Test
    public void historyAPI() throws Exception {
        List<TransactionHistoryResponse> transactionHistories = futuresRestClient.historyAPI().getTransactionHistories(SYMBOL);
        assertThat(transactionHistories.size(), greaterThan(0));
    }

    @Test
    public void indexAPI() throws Exception {
        HasMoreResponse<InterestRateResponse> interestRateList = futuresRestClient.indexAPI()
                .getInterestRateList(SYMBOL, null, null, hasMoreRequest);
        assertThat(interestRateList, notNullValue());
    }

    @Test
    @Ignore
    public void serviceStatusAPI() throws Exception {
        ServiceStatusResponse serviceStatus = futuresRestClient.serviceStatusAPI().getServiceStatus();
        assertThat(serviceStatus, notNullValue());
    }

    @Test
    public void kChartAPI() throws Exception {
        List<List<String>> kChart = futuresRestClient.kChartAPI().getKChart(SYMBOL, 60, null, null);
        assertThat(kChart, notNullValue());
    }

    @Test
    public void timeAPI() throws Exception {
        Long serverTimeStamp = futuresRestClient.timeAPI().getServerTimeStamp();
        assertThat(System.currentTimeMillis() - serverTimeStamp, lessThanOrEqualTo(5000L));
    }

    private OrderCreateResponse placeCannotDealLimitOrder() throws IOException {
        OrderCreateApiRequest pageRequest = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        return futuresRestClient.orderAPI().createOrder(pageRequest);
    }

    private OrderCreateResponse placeCannotTriggerStopOrder() throws IOException {
        OrderCreateApiRequest pageRequest = OrderCreateApiRequest.builder()
                .stop("down").stopPriceType("MP").stopPrice(BigDecimal.valueOf(1000))
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        return futuresRestClient.orderAPI().createOrder(pageRequest);
    }

}