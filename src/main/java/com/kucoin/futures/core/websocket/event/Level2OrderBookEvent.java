/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kucoin.futures.core.model.OrderBook;
import lombok.Data;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/30
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level2OrderBookEvent extends OrderBook {
}
