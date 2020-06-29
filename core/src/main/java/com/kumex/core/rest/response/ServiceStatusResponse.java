/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/28
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceStatusResponse {

    private String status;

    private String msg;

}
