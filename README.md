# JAVA SDK for Kumex API
The detailed document [https://docs.kumex.com](https://docs.kucoin.com).

[![Latest Version](https://img.shields.io/github/release/Kucoin/kumex-java-sdk.svg?style=flat-square)](https://github.com/Kucoin/kumex-java-sdk/releases)

[![Build Status](https://travis-ci.org/Kucoin/kumex-java-sdk.svg?branch=master)](https://travis-ci.org/Kucoin/kumex-java-sdk)

## Installation
1. Install library into your Maven's local repository by running `mvn install`
2. Add the following **Maven dependency** to your project's pom.xml:

```java
<dependency>
    <groupId>com.kumex</groupId>
    <artifactId>kumex-java-sdk</artifactId>
    <version>1.1.0</version>
</dependency>
```
## Usage
### Build Client
```java
KumexClientBuilder builder = new KumexClientBuilder().withBaseUrl("https://sandbox-api.kumex.com").withApiKey("YOUR_API_KEY", "YOUR_SECRET", "YOUR_PASS_PHRASE");
KumexRestClient kumexRestClient = builder.buildRestClient();
KumexPrivateWSClient kumexPrivateWSClient = builder.buildPrivateWSClient();
KumexPublicWSClient kumexPublicWSClient = builder.buildPublicWSClient();

```
You can use `withBaseUrl` method to change evironment.

| **Environment** | **BaseUri** |
| -------- | -------- |
| *Production* `DEFAULT` | https://api.kumex.com |
| *Sandbox* | https://sandbox-api.kumex.com |

If you only need to use the public web socket client or REST client public method, you can igonre `withApiKey` method. To customize your own API implementation, you may use the `with*API` method we provided for you.

## Example

### REST API

#### User
You need to sign the request to use the private user API.
##### Accounts
```java
kumexRestClient.accountAPI().accountOverview();
kumexRestClient.accountAPI().transactionHistory(type, pageRequest);          
```
##### Deposits
```java
kumexRestClient.depositAPI().getDepositAddress(currency);
kumexRestClient.depositAPI().getDepositAddressList(status, pageRequest);
```
##### Withdrawals
```java
kumexRestClient.withdrawalAPI().withdrawalAPI().getWithdrawList(status, pageRequest);
kumexRestClient.withdrawalAPI().getWithdrawQuotas(currency);
WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address(address)
                .amount(amount).currency(currency).build();
kumexRestClient.withdrawalAPI().withdrawFunds(withdrawApplyRequest);
kumexRestClient.withdrawalAPI().cancelWithdraw(withdrawalId);
```
##### Transfer
```java
kumexRestClient.transferAPI().getTransferOutRecords(status, pageRequest);
kumexRestClient.transferAPI().toKucoinMainAccount(bizNo, amount);
kumexRestClient.transferAPI().cancelTransferOutRequest(applyId)
```
#### Trade
You need to sign the request to use the private trade API.
##### Orders
```java
OrderCreateApiRequest createRequest = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(1000)).size(BigDecimal.ONE).side("buy").leverage("5")
                .symbol(SYMBOL).type("limit").clientOid(UUID.randomUUID().toString()).build();
kumexRestClient.orderAPI().getOrderDetail(orderId);
kumexRestClient.orderAPI().cancelOrder(orderId);
kumexRestClient.orderAPI().cancelAllOrders(symbol);
kumexRestClient.orderAPI().getUntriggeredStopOrderList(symbol, sid, type, pageRequest);
kumexRestClient.orderAPI().cancelAllStopOrders(symbol);
kumexRestClient.orderAPI().getOrderList(symbol, side, type, status, pageRequest);
kumexRestClient.orderAPI().getRecentDoneOrders();
```
##### Fills
```java
kumexRestClient.fillAPI().getFills(symbol, orderId, side, type, pageRequest);
kumexRestClient.fillAPI().recentFills();
kumexRestClient.fillAPI().calActiveOrderValue(symbol);
```
##### Positions
```java
kumexRestClient.positionAPI().getPosition(symbol);
kumexRestClient.positionAPI().getPositions();
kumexRestClient.positionAPI().setAutoDepositMargin(symbol, status);
kumexRestClient.positionAPI().addMarginManually(symbol, margin, bizNo);
```
##### Funding Fees
```java
kumexRestClient.fundingFeeAPI().getFundingHistory(symbol, reverse, forward, hasMoreRequest);
```
#### Market Date
Market data is public and can be used without a signed request.
##### Symbols
```java
kumexRestClient.symbolAPI().getContract(symbol);
kumexRestClient.symbolAPI().getOpenContractList();
```
##### Ticker
```java
kumexRestClient.symbolAPI().tickerAPI().getTicker(symbol);
```
##### Order Book
```java
kumexRestClient.orderBookAPI().getFullLevel2OrderBook(symbol);
kumexRestClient.orderBookAPI().getLevel2PullingMessages(symbol, sequenceStart, sequenceEnd);
kumexRestClient.orderBookAPI().getFullLevel3OrderBook(symbol);
kumexRestClient.orderBookAPI().getLevel3PullingMessages(symbol, sequenceStart, sequenceEnd);
```
##### Histories
```java
kumexRestClient.historyAPI().getTransactionHistories(symbol)
```
##### Index
```java
kumexRestClient.indexAPI().getInterestRateList(symbol, reverse, forward, hasMoreRequest);
```
##### Time
```java
kumexRestClient.timeAPI().getServerTimeStamp();
```
### Websocket Feed
Use `KumexClientBuilder` to build an instance of the websocket client. Private channel client need to pass the API Key + Secret + Pass Phreas.

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
kumexPublicWSClient.onTicker(response -> {
            System.out.println(response);
        }, symbols);
kumexPublicWSClient.onLevel2Data(response -> {
            System.out.println(response);
        }, symbols);
kumexPublicWSClient.onExecutionData(response -> {
            System.out.println(response);
        }, symbols);
kumexPublicWSClient.onLevel3Data(response -> {
            System.out.println(response);
        }, symbols);
kumexPublicWSClient.onContractMarketData(response -> {
            System.out.println(response);
        }, symbols);
kumexPublicWSClient.onTransactionStatistic(response -> {
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
