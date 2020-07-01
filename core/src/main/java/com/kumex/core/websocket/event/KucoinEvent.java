/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KucoinEvent<T> {

    private String id;

    private String userId;

    private String type;

    private String topic;

    private String subject;

    private String channelType;

    private Boolean privateChannel;

    private Boolean response;

    private T data;

}
