# JAVA SDK for KucoinFutures API
The detailed document [https://docs.kucoin.com/futures/](https://docs.kucoin.com/futures/).

[![Latest Version](https://img.shields.io/github/release/Kucoin/kucoin-futures-java-sdk.svg?style=flat-square)](https://github.com/Kucoin/kucoin-futures-java-sdk/releases)

[![Build Status](https://travis-ci.org/Kucoin/kucoin-futures-java-sdk.svg?branch=master)](https://travis-ci.org/Kucoin/kucoin-futures-java-sdk)

## Installation
1. Install library into your Maven's local repository by running `mvn install`
2. Add the following **Maven dependency** to your project's pom.xml:

```java
<dependency>
    <groupId>com.kucoin.futures</groupId>
    <artifactId>kucoin-futures-java-sdk</artifactId>
    <version>1.2.4</version>
</dependency>
```
## Usage
### Build Client
```java
KucoinFuturesClientBuilder builder = new KucoinFuturesClientBuilder().withBaseUrl("https://api-sandbox-futures.kucoin.com").withApiKey("YOUR_API_KEY", "YOUR_SECRET", "YOUR_PASS_PHRASE");
KucoinFuturesRestClient kucoinFuturesRestClient = builder.buildRestClient();
KucoinFuturesPrivateWSClient kucoinFuturesPrivateWSClient = builder.buildPrivateWSClient();
KucoinFuturesPublicWSClient kucoinFuturesPublicWSClient = builder.buildPublicWSClient();

```
You can use `withBaseUrl` method to change evironment.

| **Environment** | **BaseUri** |
| -------- | -------- |
| *Production* `DEFAULT` | https://api-futures.kucoin.com |
If you only need to use the public web socket client or REST client public method, you can igonre `withApiKey` method. To customize your own API implementation, you may use the `with*API` method we provided for you.

## Example

### REST API

#### User
You need to sign the request to use the private user API.
##### Accounts
```java
kucoinFuturesRestClient.accountAPI().accountOverview();
kucoinFuturesRestClient.accountAPI().transactionHistory(type, pageRequest);          
```
##### Deposits
```java
kucoinFuturesRestClient.depositAPI().getDepositAddress(currency);
kucoinFuturesRestClient.depositAPI().getDepositAddressList(status, pageRequest);
```
##### Withdrawals
```java
kucoinFuturesRestClient.withdrawalAPI().withdrawalAPI().getWithdrawList(status, pageRequest);
kucoinFuturesRestClient.withdrawalAPI().getWithdrawQuotas(currency);
WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address(address)
                .amount(amount).currency(currency).build();
kucoinFuturesRestClient.withdrawalAPI().withdrawFunds(withdrawApplyRequest);
kucoinFuturesRestClient.withdrawalAPI().cancelWithdraw(withdrawalId);
```
##### Transfer
```java
kucoinFuturesRestClient.transferAPI().getTransferOutRecords(status, pageRequest);
kucoinFuturesRestClient.transferAPI().toKucoinMainAccount(bizNo, amount);
kucoinFuturesRestClient.transferAPI().cancelTransferOutRequest(applyId)
```
#### Trade
You need to sign the request to use the private trade API.
##### Orders
```java
OrderCreateApiRequest createRequest = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
kucoinFuturesRestClient.orderAPI().getOrderDetail(orderId);
kucoinFuturesRestClient.orderAPI().cancelOrder(orderId);
kucoinFuturesRestClient.orderAPI().cancelAllOrders(symbol);
kucoinFuturesRestClient.orderAPI().getUntriggeredStopOrderList(symbol, sid, type, pageRequest);
kucoinFuturesRestClient.orderAPI().cancelAllStopOrders(symbol);
kucoinFuturesRestClient.orderAPI().getOrderList(symbol, side, type, status, pageRequest);
kucoinFuturesRestClient.orderAPI().getRecentDoneOrders();
```
##### Fills
```java
kucoinFuturesRestClient.fillAPI().getFills(symbol, orderId, side, type, pageRequest);
kucoinFuturesRestClient.fillAPI().recentFills();
kucoinFuturesRestClient.fillAPI().calActiveOrderValue(symbol);
```
##### Positions
```java
kucoinFuturesRestClient.positionAPI().getPosition(symbol);
kucoinFuturesRestClient.positionAPI().getPositions();
kucoinFuturesRestClient.positionAPI().setAutoDepositMargin(symbol, status);
kucoinFuturesRestClient.positionAPI().addMarginManually(symbol, margin, bizNo);
```
##### Funding Fees
```java
kucoinFuturesRestClient.fundingFeeAPI().getFundingHistory(symbol, reverse, forward, hasMoreRequest);
```
#### Market Date
Market data is public and can be used without a signed request.
##### Symbols
```java
kucoinFuturesRestClient.symbolAPI().getContract(symbol);
kucoinFuturesRestClient.symbolAPI().getOpenContractList();
```
##### Ticker
```java
kucoinFuturesRestClient.symbolAPI().tickerAPI().getTicker(symbol);
```
##### Order Book
```java
kucoinFuturesRestClient.orderBookAPI().getFullLevel2OrderBook(symbol);
kucoinFuturesRestClient.orderBookAPI().getLevel2PullingMessages(symbol, sequenceStart, sequenceEnd);
kucoinFuturesRestClient.orderBookAPI().getFullLevel3OrderBook(symbol);
kucoinFuturesRestClient.orderBookAPI().getLevel3PullingMessages(symbol, sequenceStart, sequenceEnd);
```
##### Histories
```java
kucoinFuturesRestClient.historyAPI().getTransactionHistories(symbol)
```
##### Index
```java
kucoinFuturesRestClient.indexAPI().getInterestRateList(symbol, reverse, forward, hasMoreRequest);
```
##### Time
```java
kucoinFuturesRestClient.timeAPI().getServerTimeStamp();
```
### Websocket Feed
Use `KucoinFuturesClientBuilder` to build an instance of the websocket client. Private channel client need to pass the API Key + Secret + Pass Phreas.

The Websocket client uses `ChooseServerStrategy` to choose server for connection. If you don't plan to use `builder.withChooseServerStrategy` to set your own strategy, you may use the strategy we provided by random.

#### Ping
```java
wsClient.ping(requestId)
```
#### Unsubscribe
```java
wsClient.unsubscribe(channelEnum, symbols);
```
#### Close Client
```java
wsClient.close();
```
#### Public Channels

##### Listen for changes to the order boock for ETH-BTC and KCS-BTC

```java
kucoinFuturesPublicWSClient.onTicker(response -> {
            System.out.println(response);
        }, symbols);
kucoinFuturesPublicWSClient.onLevel2Data(response -> {
            System.out.println(response);
        }, symbols);
kucoinFuturesPublicWSClient.onExecutionData(response -> {
            System.out.println(response);
        }, symbols);
kucoinFuturesPublicWSClient.onLevel3Data(response -> {
            System.out.println(response);
        }, symbols);
kucoinFuturesPublicWSClient.onContractMarketData(response -> {
            System.out.println(response);
        }, symbols);
kucoinFuturesPublicWSClient.onTransactionStatistic(response -> {
            System.out.println(response);
        }, symbols);
```
#### Private Channels

##### Listen for stop order activate message
```java
kucoinPrivateWSClient.onStopOrderActivate(response -> {
            System.out.println(response);
        });
```

##### Listen for account balance changes
```java
kucoinPrivateWSClient.onAccountBalance(response -> {
            System.out.println(response);
        });
```

##### Listen for position change message
```java
kucoinPrivateWSClient.onPositionChange(response -> {
            System.out.println(response);
        }, symbols);
```

## Testing
To contribute code or modify the library, you may enter the following command to run the test suite:

```java
mvn clean test
```
## License
[MIT](LICENSE)
