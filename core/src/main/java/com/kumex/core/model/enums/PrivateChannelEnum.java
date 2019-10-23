/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.model.enums;

import com.kumex.core.constants.APIConstants;
import lombok.Getter;

/**
 * Created by chenshiwei on 2019/1/23.
 */
@Getter
public enum PrivateChannelEnum {

    STOP_ORDER(APIConstants.API_ACTIVATE_TOPIC_PREFIX),

    ACCOUNT(APIConstants.API_BALANCE_TOPIC_PREFIX),

    POSITION(APIConstants.API_POSITION_TOPIC_PREFIX);

    private String topicPrefix;

    PrivateChannelEnum(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
}
