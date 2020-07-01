/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.request;

import lombok.Builder;
import lombok.Data;

/**
 * request include time range
 *
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/8/14
 */
@Data
@Builder
public class DuringPageRequest {

    private Long starAt;

    private Long endAt;

    @Builder.Default
    private int currentPage = 1;

    @Builder.Default
    private int pageSize = 10;

}
