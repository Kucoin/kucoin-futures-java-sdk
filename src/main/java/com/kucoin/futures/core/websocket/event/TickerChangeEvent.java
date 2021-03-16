/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket.event;

import com.kucoin.futures.core.rest.response.TickerResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenshiwei on 2019/1/23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TickerChangeEvent extends TickerResponse {
}
