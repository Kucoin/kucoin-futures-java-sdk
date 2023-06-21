package com.kucoin.futures.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderChangeEvent {

    private String orderId;
    private String symbol;
    private String type;
    private String status;
    private BigDecimal matchSize;
    private BigDecimal matchPrice;
    private String orderType;
    private String side;
    private BigDecimal price;
    private BigDecimal size;
    private BigDecimal remainSize;
    private BigDecimal filledSize;
    private BigDecimal canceledSize;
    private String tradeId;
    private String clientOid;
    private long orderTime;
    private BigDecimal oldSize;
    private String liquidity;
    private long ts;

}
