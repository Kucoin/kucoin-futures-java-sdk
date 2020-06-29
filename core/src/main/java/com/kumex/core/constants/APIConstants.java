/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.constants;

/**
 * Created by zicong.lu on 2018/12/14.
 */
public class APIConstants {

    private APIConstants() {}

    public static final String API_BASE_URL = "https://api.kumex.com/";

    public static final String USER_API_SECRET = "KC-API-SECRET";

    public static final String API_HEADER_KEY = "KC-API-KEY";
    public static final String API_HEADER_SIGN = "KC-API-SIGN";
    public static final String API_HEADER_PASSPHRASE = "KC-API-PASSPHRASE";
    public static final String API_HEADER_TIMESTAMP = "KC-API-TIMESTAMP";

    public static final String API_TICKER_TOPIC_PREFIX = "/contractMarket/ticker:";
    public static final String API_LEVEL2_TOPIC_PREFIX = "/contractMarket/level2:";
    public static final String API_EXECUTION_TOPIC_PREFIX = "/contractMarket/execution:";
    public static final String API_LEVEL3_TOPIC_PREFIX = "/contractMarket/level3:";
    public static final String API_CONTRACT_TOPIC_PREFIX = "/contract/instrument:";
    public static final String API_ANNOUNCEMENT_TOPIC_PREFIX = "/contract/announcement";
    public static final String API_TRANSACTION_TOPIC_PREFIX = "/contractMarket/snapshot:";

    public static final String API_ACTIVATE_TOPIC_PREFIX = "/contractMarket/stopOrder";
    public static final String API_LIFECYCLE_TOPIC_PREFIX = "/contractMarket/advancedOrders";
    public static final String API_BALANCE_TOPIC_PREFIX = "/contractAccount/wallet";
    public static final String API_POSITION_TOPIC_PREFIX = "/contract/position:";
}
