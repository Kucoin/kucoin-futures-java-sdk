/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.factory;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class KucoinFuturesObjectMapper {

    public static final ObjectMapper INSTANCE;
  
    static {
        INSTANCE = new ObjectMapper();
        INSTANCE.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}