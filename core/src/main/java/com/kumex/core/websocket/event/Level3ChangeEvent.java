/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kumex.core.model.Level3Message;
import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level3ChangeEvent extends Level3Message {

}
