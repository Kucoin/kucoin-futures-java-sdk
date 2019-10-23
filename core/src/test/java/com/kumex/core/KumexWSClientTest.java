/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core;

import com.kumex.core.model.enums.PrivateChannelEnum;
import com.kumex.core.model.enums.PublicChannelEnum;
import com.kumex.core.rest.request.OrderCreateApiRequest;
import com.kumex.core.rest.response.MarkPriceResponse;
import com.kumex.core.rest.response.OrderCreateResponse;
import com.kumex.core.rest.response.TickerResponse;
import com.kumex.core.websocket.event.AccountChangeEvent;
import com.kumex.core.websocket.event.ContractMarketEvent;
import com.kumex.core.websocket.event.ExecutionChangeEvent;
import com.kumex.core.websocket.event.Level2ChangeEvent;
import com.kumex.core.websocket.event.Level3ChangeEvent;
import com.kumex.core.websocket.event.PositionChangeEvent;
import com.kumex.core.websocket.event.StopOrderActivateEvent;
import com.kumex.core.websocket.event.TransactionStatisticEvent;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * Run with -Dorg.slf4j.simpleLogger.defaultLogLevel=debug for debug logging
 *
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/21
 */
public class KumexWSClientTest {

    private static KumexRestClient kumexRestClient;
    private static KumexPrivateWSClient kumexPrivateWSClient;

    private static final String SYMBOL = "XBTUSDM";

    @BeforeClass
    public static void setupClass() throws Exception {
        KumexClientBuilder builder = new KumexClientBuilder().withBaseUrl("https://sandbox-api.kumex.com")
                .withApiKey("5da5345c5a78b400087f0779", "5aaa3efb-3b9a-4849-9a35-040712d7d108", "Abc123456");
        kumexRestClient = builder.buildRestClient();
        kumexPrivateWSClient = builder.buildPrivateWSClient();
        kumexPrivateWSClient.connect();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kumexPrivateWSClient.close();
    }

    @Test
    public void onOrderActivate() throws Exception {
        AtomicReference<StopOrderActivateEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onStopOrderActivate(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PrivateChannelEnum.STOP_ORDER, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        MarkPriceResponse currentMarkPrice = kumexRestClient.indexAPI().getCurrentMarkPrice(SYMBOL);
        BigDecimal marketPrice = currentMarkPrice.getValue();

        placeStopOrder(marketPrice.add(BigDecimal.ONE), "up");
        placeStopOrder(marketPrice.subtract(BigDecimal.ONE), "down");

        boolean await = gotEvent.await(20, TimeUnit.SECONDS);
        kumexRestClient.orderAPI().cancelAllLimitOrders(SYMBOL);
        kumexRestClient.orderAPI().cancelAllStopOrders(SYMBOL);

        assertTrue(await);
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void onAccountBalance() throws Exception {
        AtomicReference<AccountChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onAccountBalance(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PrivateChannelEnum.ACCOUNT);
            gotEvent.countDown();
        });

        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void onPositionChange() throws Exception {
        AtomicReference<PositionChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onPositionChange(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PrivateChannelEnum.POSITION, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void ping() throws Exception {
        String requestId = "1234567890";
        String ping = kumexPrivateWSClient.ping(requestId);
        assertThat(ping, Is.is(requestId));
    }

    @Test
    public void onTicker() throws Exception {
        AtomicReference<TickerResponse> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onTicker(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PublicChannelEnum.TICKER, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        // Make some actual executions
        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void onLevel2Data() throws Exception {
        AtomicReference<Level2ChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onLevel2Data(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PublicChannelEnum.LEVEL2, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void onMatchExecutionData() throws Exception {
        AtomicReference<ExecutionChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onExecutionData(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PublicChannelEnum.MATCH, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        // Make some actual executions
        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void onLevel3Data() throws Exception {
        AtomicReference<Level3ChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onLevel3Data(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PublicChannelEnum.LEVEL3, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void onContractMarketData() throws Exception {
        AtomicReference<ContractMarketEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onContractMarketData(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PublicChannelEnum.CONTRACT_MARKET, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    @Test
    public void onTransactionStatistic() throws Exception {
        AtomicReference<TransactionStatisticEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kumexPrivateWSClient.onTransactionStatistic(response -> {
            event.set(response.getData());
            kumexPrivateWSClient.unsubscribe(PublicChannelEnum.TRANSACTION_STATISTIC, SYMBOL);
            gotEvent.countDown();
        }, SYMBOL);

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        assertThat(event.get(), notNullValue());
    }

    private void placeOrderAndCancelOrder() throws InterruptedException, IOException {
        Thread.sleep(1000);
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse order = kumexRestClient.orderAPI().createOrder(request);
        kumexRestClient.orderAPI().cancelOrder(order.getOrderId());
    }

    private void buyAndSell() throws InterruptedException, IOException {
        Thread.sleep(1000);
        OrderCreateApiRequest request1 = OrderCreateApiRequest.builder()
                .size(BigDecimal.ONE)
                .side("buy")
                .symbol(SYMBOL)
                .type("market")
                .leverage("5")
                .clientOid(UUID.randomUUID().toString())
                .build();
        kumexRestClient.orderAPI().createOrder(request1);
        OrderCreateApiRequest request2 = OrderCreateApiRequest.builder()
                .size(BigDecimal.ONE)
                .side("sell")
                .symbol(SYMBOL)
                .type("market")
                .leverage("5")
                .clientOid(UUID.randomUUID().toString())
                .build();
        kumexRestClient.orderAPI().createOrder(request2);
    }

    private OrderCreateResponse placeStopOrder(BigDecimal stopPrice, String stop) throws IOException {
        OrderCreateApiRequest pageRequest = OrderCreateApiRequest.builder()
                .stop(stop).stopPriceType("MP").stopPrice(stopPrice)
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
        return kumexRestClient.orderAPI().createOrder(pageRequest);
    }
}
