/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.model.enums;

import com.kucoin.futures.core.constants.APIConstants;
import lombok.Getter;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Getter
public enum PublicChannelEnum {

    TICKER(APIConstants.API_TICKER_TOPIC_PREFIX),

    LEVEL2(APIConstants.API_LEVEL2_TOPIC_PREFIX),

    LEVEL2_DEPTH_5(APIConstants.API_LEVEL2_DEPTH_5_PREFIX),

    LEVEL2_DEPTH_50(APIConstants.API_LEVEL2_DEPTH_50_PREFIX),

    MATCH(APIConstants.API_EXECUTION_TOPIC_PREFIX),

    LEVEL3_V2(APIConstants.API_LEVEL3_V2_TOPIC_PREFIX),

    CONTRACT_MARKET(APIConstants.API_CONTRACT_TOPIC_PREFIX),

    ANNOUNCEMENT(APIConstants.API_ANNOUNCEMENT_TOPIC_PREFIX),

    TRANSACTION_STATISTIC(APIConstants.API_TRANSACTION_TOPIC_PREFIX);

    private String topicPrefix;

    PublicChannelEnum(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
}
