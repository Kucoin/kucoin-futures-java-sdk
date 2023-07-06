/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenshiwei
 * @since 2019/10/18
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnouncementEvent {

    private String symbol;

    private long fundingTime;

    private BigDecimal fundingRate;

    private long timestamp;

}
