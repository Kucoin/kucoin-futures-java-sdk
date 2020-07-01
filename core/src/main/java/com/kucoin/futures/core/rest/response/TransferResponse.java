/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Transfer response
 *
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/8/14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferResponse {

    private String applyId;

}
