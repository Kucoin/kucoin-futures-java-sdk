/*
 * Copyright 2019 Mek Global Limited
 */
package com.kumex.core;

import com.kumex.core.exception.KumexApiException;
import com.kumex.core.model.Level2Message;
import com.kumex.core.model.Level3Message;
import com.kumex.core.rest.request.DuringHasMoreRequest;
import com.kumex.core.rest.request.DuringPageRequest;
import com.kumex.core.rest.request.OrderCreateApiRequest;
import com.kumex.core.rest.request.WithdrawApplyRequest;
import com.kumex.core.rest.response.AccountOverviewResponse;
import com.kumex.core.rest.response.ActiveOrderResponse;
import com.kumex.core.rest.response.ContractResponse;
import com.kumex.core.rest.response.FillResponse;
import com.kumex.core.rest.response.FundingHistoryResponse;
import com.kumex.core.rest.response.HasMoreResponse;
import com.kumex.core.rest.response.InterestRateResponse;
import com.kumex.core.rest.response.OrderBookResponse;
import com.kumex.core.rest.response.OrderCancelResponse;
import com.kumex.core.rest.response.OrderCreateResponse;
import com.kumex.core.rest.response.OrderResponse;
import com.kumex.core.rest.response.Pagination;
import com.kumex.core.rest.response.PositionResponse;
import com.kumex.core.rest.response.TickerResponse;
import com.kumex.core.rest.response.TransactionHistory;
import com.kumex.core.rest.response.TransactionHistoryResponse;
import com.kumex.core.rest.response.TransferHistory;
import com.kumex.core.rest.response.TransferResponse;
import com.kumex.core.rest.response.WithdrawQuotaResponse;
import com.kumex.core.rest.response.WithdrawResponse;
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
public class KumexRestClientTest {
    private static KumexRestClient sandboxKumexRestClient;

    private static Long startAt;
    private static Long endAt;

    private static DuringPageRequest pageRequest;
    private static DuringHasMoreRequest hasMoreRequest;

    private final static String SYMBOL = "XBTUSDM";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        sandboxKumexRestClient = new KumexClientBuilder().withBaseUrl("https://sandbox-api.kumex.com")
                .withApiKey("5da5345c5a78b400087f0779", "5aaa3efb-3b9a-4849-9a35-040712d7d108", "Abc123456")
                .buildRestClient();
        startAt = LocalDateTime.of(2019, 9, 1, 0, 0, 0).atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        endAt = LocalDateTime.of(2019, 10, 30, 0, 0, 0).atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        pageRequest = DuringPageRequest.builder().starAt(startAt).endAt(endAt).currentPage(1).pageSize(10).build();
        hasMoreRequest = DuringHasMoreRequest.builder().starAt(startAt).endAt(endAt).offset(0).maxCount(10).build();
    }

    @Test
    public void accountAPI() throws Exception {
        AccountOverviewResponse accountOverviewResponse = sandboxKumexRestClient.accountAPI().accountOverview();
        assertThat(accountOverviewResponse, notNullValue());

        HasMoreResponse<TransactionHistory> transactionHistoryHasMoreResponse = sandboxKumexRestClient.accountAPI()
                .transactionHistory(null, null);
        assertThat(transactionHistoryHasMoreResponse, notNullValue());
        assertThat(transactionHistoryHasMoreResponse.isHasMore(), Is.is(false));
    }

    @Test
    @Ignore
    public void depositAPI() throws Exception {

        exception.expect(KumexApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKumexRestClient.depositAPI().getDepositAddress("XBT");

        exception.expect(KumexApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKumexRestClient.depositAPI().getDepositList("SUCCESS", pageRequest);
    }

    @Test
    @Ignore
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = sandboxKumexRestClient.withdrawalAPI()
                .getWithdrawList("FAILURE", null);
        assertThat(withdrawList, notNullValue());

        WithdrawQuotaResponse kcs = sandboxKumexRestClient.withdrawalAPI().getWithdrawQuotas("XBT");
        assertThat(kcs, notNullValue());

        exception.expect(KumexApiException.class);
        exception.expectMessage("Sandbox environment cannot be withdrawn");
        WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address("123467")
                .amount(BigDecimal.valueOf(0.00000001)).currency("XBT").build();
        sandboxKumexRestClient.withdrawalAPI().withdrawFunds(withdrawApplyRequest);

        sandboxKumexRestClient.withdrawalAPI().cancelWithdraw("1234567");
    }

    @Test
    @Ignore
    public void transferAPI() throws Exception {
        Pagination<TransferHistory> records = sandboxKumexRestClient.transferAPI().getTransferOutRecords("SUCCESS", pageRequest);
        assertThat(records.getItems().size(), is(0));

        TransferResponse transferResponse = sandboxKumexRestClient.transferAPI().toKucoinMainAccount("123456", BigDecimal.valueOf(0.00000001));
        assertThat(transferResponse.getApplyId(), notNullValue());

        sandboxKumexRestClient.transferAPI().cancelTransferOutRequest(transferResponse.getApplyId());
    }

    @Test
    public void orderAPI() throws Exception {
        OrderCreateResponse order = placeCannotDealLimitOrder();
        assertThat(order, notNullValue());

        OrderResponse orderDetail = sandboxKumexRestClient.orderAPI().getOrderDetail(order.getOrderId());
        assertThat(orderDetail, notNullValue());

        OrderCancelResponse orderCancelResponse = sandboxKumexRestClient.orderAPI().cancelOrder(order.getOrderId());
        assertThat(orderCancelResponse.getCancelledOrderIds().size(), is(1));

        placeCannotDealLimitOrder();
        OrderCancelResponse cancelAllLimitOrders = sandboxKumexRestClient.orderAPI().cancelAllLimitOrders(SYMBOL);
        assertThat(cancelAllLimitOrders.getCancelledOrderIds().size(), greaterThan(0));

        placeCannotTriggerStopOrder();
        Pagination<OrderResponse> stopOrderList = sandboxKumexRestClient.orderAPI().getUntriggeredStopOrderList(SYMBOL,
                "buy", "limit", pageRequest);
        assertThat(stopOrderList.getItems().size(), greaterThan(0));

        OrderCancelResponse cancelAllStopOrders = sandboxKumexRestClient.orderAPI().cancelAllStopOrders(SYMBOL);
        assertThat(cancelAllStopOrders.getCancelledOrderIds().size(), greaterThan(0));

        Pagination<OrderResponse> orderList = sandboxKumexRestClient.orderAPI().getOrderList(SYMBOL, "buy",
                "limit", "done", null);
        assertThat(orderList.getItems().size(), greaterThan(0));

        List<OrderResponse> recentDoneOrders = sandboxKumexRestClient.orderAPI().getRecentDoneOrders();
        assertThat(recentDoneOrders.size(), greaterThan(0));

    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<FillResponse> fills = sandboxKumexRestClient.fillAPI().getFills(null, null, null,
                null, null);
        assertThat(fills, notNullValue());

        List<FillResponse> fillResponses = sandboxKumexRestClient.fillAPI().recentFills();
        assertThat(fillResponses, notNullValue());

        ActiveOrderResponse xbtusdm = sandboxKumexRestClient.fillAPI().calActiveOrderValue(SYMBOL);
        assertThat(xbtusdm, notNullValue());
    }

    @Test
    public void positionAPI() throws Exception {
        PositionResponse position = sandboxKumexRestClient.positionAPI().getPosition(SYMBOL);
        assertThat(position, notNullValue());

        List<PositionResponse> positions = sandboxKumexRestClient.positionAPI().getPositions();
        assertThat(positions, notNullValue());

        if (!positions.isEmpty()) {
            sandboxKumexRestClient.positionAPI().setAutoDepositMargin(SYMBOL, true);
            sandboxKumexRestClient.positionAPI().addMarginManually(SYMBOL, BigDecimal.valueOf(0.000001), "123456");
        }

    }

    @Test
    public void fundingFeeAPI() throws Exception {
        HasMoreResponse<FundingHistoryResponse> fundingHistory = sandboxKumexRestClient.fundingFeeAPI()
                .getFundingHistory(SYMBOL, null, null, hasMoreRequest);
        assertThat(fundingHistory, notNullValue());
    }

    @Test
    public void symbolAPI() throws Exception {
        ContractResponse contract = sandboxKumexRestClient.symbolAPI().getContract(SYMBOL);
        assertThat(contract, notNullValue());

        List<ContractResponse> openContractList = sandboxKumexRestClient.symbolAPI().getOpenContractList();
        assertThat(openContractList.size(), greaterThan(0));
    }

    @Test
    public void tickerAPI() throws Exception {
        TickerResponse ticker = sandboxKumexRestClient.tickerAPI().getTicker(SYMBOL);
        assertThat(ticker, notNullValue());
    }

    @Test
    public void orderBookAPI() throws Exception {
        OrderBookResponse fullOrderBookAggregated = sandboxKumexRestClient.orderBookAPI().getFullLevel2OrderBook(SYMBOL);
        assertThat(fullOrderBookAggregated, notNullValue());

        List<Level2Message> level2PullingMessages = sandboxKumexRestClient.orderBookAPI().getLevel2PullingMessages(SYMBOL,
                fullOrderBookAggregated.getSequence() - 1, fullOrderBookAggregated.getSequence());
        assertThat(level2PullingMessages.size(), is(2));

        OrderBookResponse fullOrderBookAtomic = sandboxKumexRestClient.orderBookAPI().getFullLevel3OrderBook(SYMBOL);
        assertThat(fullOrderBookAtomic, notNullValue());

        List<Level3Message> level3PullingMessages = sandboxKumexRestClient.orderBookAPI().getLevel3PullingMessages(SYMBOL,
                fullOrderBookAtomic.getSequence() - 1, fullOrderBookAtomic.getSequence());
        assertThat(level3PullingMessages.size(), is(2));
    }

    @Test
    public void historyAPI() throws Exception {
        List<TransactionHistoryResponse> transactionHistories = sandboxKumexRestClient.historyAPI().getTransactionHistories(SYMBOL);
        assertThat(transactionHistories.size(), greaterThan(0));
    }

    @Test
    public void indexAPI() throws Exception {
        HasMoreResponse<InterestRateResponse> interestRateList = sandboxKumexRestClient.indexAPI()
                .getInterestRateList(SYMBOL, null, null, hasMoreRequest);
        assertThat(interestRateList, notNullValue());
    }

    @Test
    public void timeAPI() throws Exception {
        Long serverTimeStamp = sandboxKumexRestClient.timeAPI().getServerTimeStamp();
        assertThat(System.currentTimeMillis() - serverTimeStamp, lessThanOrEqualTo(5000L));
    }

    private OrderCreateResponse placeCannotDealLimitOrder() throws IOException {
        OrderCreateApiRequest pageRequest = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        return sandboxKumexRestClient.orderAPI().createOrder(pageRequest);
    }

    private OrderCreateResponse placeCannotTriggerStopOrder() throws IOException {
        OrderCreateApiRequest pageRequest = OrderCreateApiRequest.builder()
                .stop("down").stopPriceType("MP").stopPrice(BigDecimal.valueOf(1000))
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        return sandboxKumexRestClient.orderAPI().createOrder(pageRequest);
    }

}