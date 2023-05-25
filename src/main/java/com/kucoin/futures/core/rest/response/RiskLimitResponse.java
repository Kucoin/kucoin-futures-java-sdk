package com.kucoin.futures.core.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName RiskLimitResponse.java
 * @Description
 * @createTime 2023/05/25日 10:45:00
 */
@Data
public class RiskLimitResponse {
    private String symbol;
    private String level;
    private String maxRiskLimit;
    private String minRiskLimit;
    private String maxLeverage;
    private String initialMargin;
    private String maintainMargin;
}
