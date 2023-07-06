/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author chenshiwei
 * @since 2019/7/26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HasMoreResponse<T> {

    private boolean hasMore;

    private List<T> dataList;

}
