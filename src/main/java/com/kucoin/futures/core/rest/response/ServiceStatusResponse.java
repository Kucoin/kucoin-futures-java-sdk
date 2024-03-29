/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author chenshiwei
 * @since 2020/6/28
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceStatusResponse {

    private String status;

    private String msg;

}
