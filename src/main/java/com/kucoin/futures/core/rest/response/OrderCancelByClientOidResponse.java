/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author blazetan
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCancelByClientOidResponse {

    private String clientOid;

}
