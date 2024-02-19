package com.kucoin.futures.core.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class TradeStatisticsResponse {

    private BigDecimal turnoverOf24h;

}
