package com.kucoin.futures.core.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class RiskLimitLevelUpdateRequest {
    private String symbol;
    private Integer level;
}
