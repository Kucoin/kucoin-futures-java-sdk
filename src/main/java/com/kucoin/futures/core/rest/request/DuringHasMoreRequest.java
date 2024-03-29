/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.request;

import lombok.Builder;
import lombok.Data;

/**
 * has more request include time range
 *
 * @author chenshiwei
 * @since 2019/8/14
 */
@Data
@Builder
public class DuringHasMoreRequest {

    private Long starAt;

    private Long endAt;

    @Builder.Default
    private long offset = 0;

    @Builder.Default
    private long maxCount = 50;

}
