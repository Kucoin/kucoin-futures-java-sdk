/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kucoin.futures.core.model.Level2Message;
import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/11.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level2ChangeEvent extends Level2Message {

}
