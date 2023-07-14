/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.exception.KucoinFuturesApiException;
import com.kucoin.futures.core.rest.request.DuringHasMoreRequest;
import com.kucoin.futures.core.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * Account API
 * @author chenshiwei
 * @since 2019/7/25
 */
public interface AccountAPI {

    /**
     * User's account overview
     *
     * This endpoint requires the General permission.
     *
     * @param currency [Optional] Currecny ,including XBT,USDT,Default XBT
     * @return The accounts.
     * @throws IOException on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    AccountOverviewResponse accountOverview(String currency) throws IOException;

    /**
     * User's all account overview
     *
     * This endpoint requires the General permission.
     *
     * @param currency [Optional] Currecny ,including XBT,USDT,Default XBT
     * @return The accounts.
     * @throws IOException on socket errors.
     */
    AccountOverviewAllResponse accountOverviewAll(String currency) throws IOException;

    /**
     * If there are open positions, the status of the first page returned will be Pending,
     * indicating the realised profit and loss in the current 8-hour settlement period.
     * Please specify the minimum offset number of the current page into the offset field to turn the page.
     *
     * @param type     [Optional] Type RealisedPNL-Realised profit and loss, Deposit-Deposit, Withdrawal-withdraw,
     *                 Transferin-Transfer in, TransferOut-Transfer out
     * @param currency [Optional] Currency of transaction history XBT or USDT
     * @param request  [Optional] include startAt endAt offset and maxCount optional parameters
     * @return The account balance.
     * @throws IOException on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    HasMoreResponse<TransactionHistory> transactionHistory(String type, String currency, DuringHasMoreRequest request)
            throws IOException;

    /**
     * This endpoint can be used to obtain a list of Futures APIs pertaining to a sub-account.
     *
     * @param subName Sub-account name
     * @param apiKey  [Optional] API-Key.
     * @return a list of Futures APIs pertaining to a sub-account
     * @throws IOException        on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    List<SubApiKeyResponse> getSubApiKey(String subName, String apiKey) throws IOException;;

    /**
     * This endpoint can be used to create Futures APIs for sub-accounts.
     *
     * @param subName     Sub-account name, create sub account name of API Key
     * @param passphrase  Password(Must contain 7-32 characters. Cannot contain any spaces.)
     * @param remark      Remarks(1~24 characters)
     * @param permission  [Optional] Permissions(Only "General" and "Trade" permissions can be set, such as "General, Trade". The default is "General")
     * @param ipWhitelist [Optional] IP whitelist(You may add up to 20 IPs. Use a halfwidth comma to each IP)
     * @param expire      [Optional] API expiration time; Never expire(default)-1，30Day30，90Day90，180Day180，360Day360
     * @return sub-api-key info
     * @throws IOException        on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    SubApiKeyResponse createSubApiKey(String subName, String passphrase, String remark, String permission, String ipWhitelist, String expire) throws IOException;;

    /**
     * This endpoint can be used to modify sub-account Futures APIs.
     *
     * @param subName     Sub-account name, create sub account name of API Key
     * @param passphrase  Password(Must contain 7-32 characters. Cannot contain any spaces.)
     * @param apiKey      API-Key(Sub-account APIKey)
     * @param permission  [Optional] Permissions(Only "General" and "Trade" permissions can be set, such as "General, Trade". The default is "General")
     * @param ipWhitelist [Optional] IP whitelist(You may add up to 20 IPs. Use a halfwidth comma to each IP)
     * @param expire      [Optional] API expiration time; Never expire(default)-1，30Day30，90Day90，180Day180，360Day360
     * @return sub-api-key info
     * @throws IOException        on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    SubApiKeyResponse updateSubApiKey(String subName, String apiKey, String passphrase, String permission, String ipWhitelist, String expire) throws IOException;;

    /**
     * This endpoint can be used to delete sub-account Futures APIs.
     *
     * @param subName    Sub-account name, create sub account name of API Key
     * @param passphrase Password(Must contain 7-32 characters. Cannot contain any spaces.)
     * @param apiKey     API-Key(Sub-account APIKey)
     * @return sub-api-key info
     * @throws IOException        on socket errors.
     * @throws KucoinFuturesApiException when errors are returned from the exchange.
     */
    SubApiKeyResponse deleteSubApiKey(String subName, String apiKey, String passphrase) throws IOException;;

}
