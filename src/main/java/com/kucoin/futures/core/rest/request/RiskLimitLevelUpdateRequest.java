package com.kucoin.futures.core.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName RiskLimitLevelUpdateRequest.java
 * @Description
 * @createTime 2023/05/25日 10:54:00
 */
@Data
public class RiskLimitLevelUpdateRequest {
    private String symbol;
    private Integer level;
}
