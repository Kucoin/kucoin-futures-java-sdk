/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.model.enums;

import com.kumex.core.constants.APIConstants;
import lombok.Getter;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Getter
public enum PublicChannelEnum {

    TICKER(APIConstants.API_TICKER_TOPIC_PREFIX),

    LEVEL2(APIConstants.API_LEVEL2_TOPIC_PREFIX),

    MATCH(APIConstants.API_EXECUTION_TOPIC_PREFIX),

    LEVEL3(APIConstants.API_LEVEL3_TOPIC_PREFIX),

    CONTRACT_MARKET(APIConstants.API_CONTRACT_TOPIC_PREFIX),

    ANNOUNCEMENT(APIConstants.API_ANNOUNCEMENT_TOPIC_PREFIX),

    TRANSACTION_STATISTIC(APIConstants.API_TRANSACTION_TOPIC_PREFIX);

    private String topicPrefix;

    PublicChannelEnum(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
}
