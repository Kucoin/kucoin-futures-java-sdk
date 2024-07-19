/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.model.enums;

import com.kucoin.futures.core.constants.APIConstants;
import lombok.Getter;

/**
 * Created by chenshiwei on 2019/1/23.
 */
@Getter
public enum PrivateChannelEnum {

    STOP_ORDER(APIConstants.API_ACTIVATE_TOPIC_PREFIX),

    ACCOUNT(APIConstants.API_BALANCE_TOPIC_PREFIX),

    POSITION(APIConstants.API_POSITION_TOPIC_PREFIX),
    POSITION_ALL(APIConstants.API_POSITION_ALL_TOPIC_PREFIX),

    SYMBOL_ORDER_CHANGE(APIConstants.API_SYMBOL_ORDER_CHANGE_TOPIC_PREFIX),

    ORDER_CHANGE(APIConstants.API_ORDER_CHANGE_TOPIC_PREFIX);

    private String topicPrefix;

    PrivateChannelEnum(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
}
