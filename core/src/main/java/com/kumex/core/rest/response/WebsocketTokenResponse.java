/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kumex.core.model.InstanceServer;

import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/15.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebsocketTokenResponse {

    private List<InstanceServer> instanceServers;

    private String token;

}
