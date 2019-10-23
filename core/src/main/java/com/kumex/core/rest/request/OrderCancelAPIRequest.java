/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.request;

import lombok.Data;
import lombok.ToString;

/**
 * 订单操作Facade Request类
 *
 * @author 屈亮
 * @since 2018-09-17
 */
@Data
@ToString
public class OrderCancelAPIRequest {

    private String symbol;
}