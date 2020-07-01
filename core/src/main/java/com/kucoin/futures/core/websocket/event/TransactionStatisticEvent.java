/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/18
 */
@Data
public class TransactionStatisticEvent {

    private BigDecimal volume;

    private BigDecimal turnover;

    private BigDecimal lastPrice;

    private BigDecimal priceChgPct;

    private long ts;

}
