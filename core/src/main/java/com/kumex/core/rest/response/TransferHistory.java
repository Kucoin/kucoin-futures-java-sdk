/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Transfer history
 *
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/8/14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferHistory {

    private String applyId;

    private String currency;

    private String status;

    private BigDecimal amount;

    private String reason;

    private long offset;

    private Date createdAt;

}
