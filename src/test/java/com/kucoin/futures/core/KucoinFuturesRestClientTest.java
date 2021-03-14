/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core;

import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;
import com.kucoin.futures.core.rest.request.OrderCreateApiRequest;
import com.kucoin.futures.core.rest.request.WithdrawApplyRequest;
import com.kucoin.futures.core.rest.response.AccountOverviewResponse;
import com.kucoin.futures.core.rest.response.ActiveOrderResponse;
import com.kucoin.futures.core.rest.response.FillResponse;
import com.kucoin.futures.core.rest.response.FundingHistoryResponse;
import com.kucoin.futures.core.rest.response.InterestRateResponse;
import com.kucoin.futures.core.rest.response.OrderCancelResponse;
import com.kucoin.futures.core.rest.response.OrderCreateResponse;
import com.kucoin.futures.core.rest.response.OrderResponse;
import com.kucoin.futures.core.rest.response.PositionResponse;
import com.kucoin.futures.core.rest.response.ServiceStatusResponse;
import com.kucoin.futures.core.rest.response.TransferHistory;
import com.kucoin.futures.core.rest.response.TransferResponse;
import com.kucoin.futures.core.rest.response.WithdrawQuotaResponse;
import com.kucoin.futures.core.exception.KucoinFuturesApiException;
import com.kucoin.futures.core.rest.request.DuringPageRequest;
import com.kucoin.futures.core.rest.response.ContractResponse;
import com.kucoin.futures.core.rest.response.HasMoreResponse;
import com.kucoin.futures.core.rest.response.OrderBookResponse;
import com.kucoin.futures.core.rest.response.Pagination;
import com.kucoin.futures.core.rest.response.TickerResponse;
import com.kucoin.futures.core.rest.response.TransactionHistory;
import com.kucoin.futures.core.rest.response.TransactionHistoryResponse;
import com.kucoin.futures.core.rest.response.WithdrawResponse;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
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

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by chenshiwei on 2019/1/21.
 */
public class KucoinFuturesRestClientTest {
    private static KucoinFuturesRestClient sandboxKucoinFuturesRestClient;

    private static Long startAt;
    private static Long endAt;

    private static DuringPageRequest pageRequest;
    private static DuringHasMoreRequest hasMoreRequest;

    private final static String SYMBOL = "XBTUSDM";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        sandboxKucoinFuturesRestClient = new KucoinFuturesClientBuilder().withBaseUrl("https://api-sandbox-futures.kucoin.com")
                .withApiKey("5da5345c5a78b400087f0779", "5aaa3efb-3b9a-4849-9a35-040712d7d108", "Abc123456")
                .buildRestClient();
        startAt = LocalDateTime.of(2019, 9, 1, 0, 0, 0).atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        endAt = LocalDateTime.of(2019, 10, 30, 0, 0, 0).atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        pageRequest = DuringPageRequest.builder().starAt(startAt).endAt(endAt).currentPage(1).pageSize(10).build();
        hasMoreRequest = DuringHasMoreRequest.builder().starAt(startAt).endAt(endAt).offset(0).maxCount(10).build();
    }

    @Test
    public void accountAPI() throws Exception {
        AccountOverviewResponse accountOverviewResponse = sandboxKucoinFuturesRestClient.accountAPI().accountOverview(null);
        assertThat(accountOverviewResponse, notNullValue());

        HasMoreResponse<TransactionHistory> transactionHistoryHasMoreResponse = sandboxKucoinFuturesRestClient.accountAPI()
                .transactionHistory(null, null, null);
        assertThat(transactionHistoryHasMoreResponse, notNullValue());
        assertThat(transactionHistoryHasMoreResponse.isHasMore(), Is.is(false));
    }

    @Test
    @Ignore
    public void depositAPI() throws Exception {

        exception.expect(KucoinFuturesApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKucoinFuturesRestClient.depositAPI().getDepositAddress("XBT");

        exception.expect(KucoinFuturesApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKucoinFuturesRestClient.depositAPI().getDepositList("SUCCESS", null, pageRequest);
    }

    @Test
    @Ignore
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = sandboxKucoinFuturesRestClient.withdrawalAPI()
                .getWithdrawList("FAILURE", null, null);
        assertThat(withdrawList, notNullValue());

        WithdrawQuotaResponse kcs = sandboxKucoinFuturesRestClient.withdrawalAPI().getWithdrawQuotas("XBT");
        assertThat(kcs, notNullValue());

        exception.expect(KucoinFuturesApiException.class);
        exception.expectMessage("Sandbox environment cannot be withdrawn");
        WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address("123467")
                .amount(BigDecimal.valueOf(0.00000001)).currency("XBT").build();
        sandboxKucoinFuturesRestClient.withdrawalAPI().withdrawFunds(withdrawApplyRequest);

        sandboxKucoinFuturesRestClient.withdrawalAPI().cancelWithdraw("1234567");
    }

    @Test
    @Ignore
    public void transferAPI() throws Exception {
        Pagination<TransferHistory> records = sandboxKucoinFuturesRestClient.transferAPI().getTransferOutRecords("SUCCESS", null, pageRequest);
        assertThat(records.getItems().size(), is(0));

        TransferResponse transferResponse = sandboxKucoinFuturesRestClient.transferAPI().toKucoinMainAccount("123456", BigDecimal.valueOf(0.00000001));
        assertThat(transferResponse.getApplyId(), notNullValue());

        sandboxKucoinFuturesRestClient.transferAPI().cancelTransferOutRequest(transferResponse.getApplyId());
    }

    @Test
    public void orderAPI() throws Exception {
        OrderCreateResponse order = placeCannotDealLimitOrder();
        assertThat(order, notNullValue());

        OrderResponse orderDetail = sandboxKucoinFuturesRestClient.orderAPI().getOrderDetail(order.getOrderId());
        assertThat(orderDetail, notNullValue());

        OrderCancelResponse orderCancelResponse = sandboxKucoinFuturesRestClient.orderAPI().cancelOrder(order.getOrderId());
        assertThat(orderCancelResponse.getCancelledOrderIds().size(), is(1));

        placeCannotDealLimitOrder();
        OrderCancelResponse cancelAllLimitOrders = sandboxKucoinFuturesRestClient.orderAPI().cancelAllLimitOrders(SYMBOL);
        assertThat(cancelAllLimitOrders.getCancelledOrderIds().size(), greaterThan(0));

        placeCannotTriggerStopOrder();
        Pagination<OrderResponse> stopOrderList = sandboxKucoinFuturesRestClient.orderAPI().getUntriggeredStopOrderList(SYMBOL,
                "buy", "limit", pageRequest);
        assertThat(stopOrderList.getItems().size(), greaterThan(0));

        OrderCancelResponse cancelAllStopOrders = sandboxKucoinFuturesRestClient.orderAPI().cancelAllStopOrders(SYMBOL);
        assertThat(cancelAllStopOrders.getCancelledOrderIds().size(), greaterThan(0));

        Pagination<OrderResponse> orderList = sandboxKucoinFuturesRestClient.orderAPI().getOrderList(SYMBOL, "buy",
                "limit", "done", null);
        assertThat(orderList.getItems().size(), greaterThan(0));

        List<OrderResponse> recentDoneOrders = sandboxKucoinFuturesRestClient.orderAPI().getRecentDoneOrders();
        assertThat(recentDoneOrders.size(), greaterThan(0));

    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<FillResponse> fills = sandboxKucoinFuturesRestClient.fillAPI().getFills(null, null, null,
                null, null);
        assertThat(fills, notNullValue());

        List<FillResponse> fillResponses = sandboxKucoinFuturesRestClient.fillAPI().recentFills();
        assertThat(fillResponses, notNullValue());

        ActiveOrderResponse xbtusdm = sandboxKucoinFuturesRestClient.fillAPI().calActiveOrderValue(SYMBOL);
        assertThat(xbtusdm, notNullValue());
    }

    @Test
    public void positionAPI() throws Exception {
        PositionResponse position = sandboxKucoinFuturesRestClient.positionAPI().getPosition(SYMBOL);
        assertThat(position, notNullValue());

        List<PositionResponse> positions = sandboxKucoinFuturesRestClient.positionAPI().getPositions();
        assertThat(positions, notNullValue());

        if (!positions.isEmpty()) {
            sandboxKucoinFuturesRestClient.positionAPI().setAutoDepositMargin(SYMBOL, true);
            sandboxKucoinFuturesRestClient.positionAPI().addMarginManually(SYMBOL, BigDecimal.valueOf(0.000001), "123456");
        }

    }

    @Test
    public void fundingFeeAPI() throws Exception {
        HasMoreResponse<FundingHistoryResponse> fundingHistory = sandboxKucoinFuturesRestClient.fundingFeeAPI()
                .getFundingHistory(SYMBOL, null, null, hasMoreRequest);
        assertThat(fundingHistory, notNullValue());
    }

    @Test
    public void symbolAPI() throws Exception {
        ContractResponse contract = sandboxKucoinFuturesRestClient.symbolAPI().getContract(SYMBOL);
        assertThat(contract, notNullValue());

        List<ContractResponse> openContractList = sandboxKucoinFuturesRestClient.symbolAPI().getOpenContractList();
        assertThat(openContractList.size(), greaterThan(0));
    }

    @Test
    public void tickerAPI() throws Exception {
        TickerResponse ticker = sandboxKucoinFuturesRestClient.tickerAPI().getTicker(SYMBOL);
        assertThat(ticker, notNullValue());
    }

    @Test
    public void orderBookAPI() throws Exception {
        OrderBookResponse fullOrderBookAggregated = sandboxKucoinFuturesRestClient.orderBookAPI().getFullLevel2OrderBook(SYMBOL);
        assertThat(fullOrderBookAggregated, notNullValue());

        OrderBookResponse fullOrderBookAtomic = sandboxKucoinFuturesRestClient.orderBookAPI().getFullLevel3OrderBook(SYMBOL);
        assertThat(fullOrderBookAtomic, notNullValue());
    }

    @Test
    public void historyAPI() throws Exception {
        List<TransactionHistoryResponse> transactionHistories = sandboxKucoinFuturesRestClient.historyAPI().getTransactionHistories(SYMBOL);
        assertThat(transactionHistories.size(), greaterThan(0));
    }

    @Test
    public void indexAPI() throws Exception {
        HasMoreResponse<InterestRateResponse> interestRateList = sandboxKucoinFuturesRestClient.indexAPI()
                .getInterestRateList(SYMBOL, null, null, hasMoreRequest);
        assertThat(interestRateList, notNullValue());
    }

    @Test
    @Ignore
    public void serviceStatusAPI() throws Exception {
        ServiceStatusResponse serviceStatus = sandboxKucoinFuturesRestClient.serviceStatusAPI().getServiceStatus();
        assertThat(serviceStatus, notNullValue());
    }

    @Test
    public void kChartAPI() throws Exception {
        List<List<String>> kChart = sandboxKucoinFuturesRestClient.kChartAPI().getKChart(SYMBOL, 60, null, null);
        assertThat(kChart, notNullValue());
    }

    @Test
    public void timeAPI() throws Exception {
        Long serverTimeStamp = sandboxKucoinFuturesRestClient.timeAPI().getServerTimeStamp();
        assertThat(System.currentTimeMillis() - serverTimeStamp, lessThanOrEqualTo(5000L));
    }

    private OrderCreateResponse placeCannotDealLimitOrder() throws IOException {
        OrderCreateApiRequest pageRequest = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        return sandboxKucoinFuturesRestClient.orderAPI().createOrder(pageRequest);
    }

    private OrderCreateResponse placeCannotTriggerStopOrder() throws IOException {
        OrderCreateApiRequest pageRequest = OrderCreateApiRequest.builder()
                .stop("down").stopPriceType("MP").stopPrice(BigDecimal.valueOf(1000))
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        return sandboxKucoinFuturesRestClient.orderAPI().createOrder(pageRequest);
    }

}