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
 * @since 2019/8/14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferResponse {

    private String applyId;

    private String bizNo;

    private String payAccountType;

    private String payTag;

    private String remark;

    private String recAccountType;

    private String recTag;

    private String recRemark;

    private String recSystem;

    private String status;

    private String currency;

    private String amount;

    private String fee;

    private String sn;

    private String reason;

    private String createdAt;

    private String updatedAt;
}
