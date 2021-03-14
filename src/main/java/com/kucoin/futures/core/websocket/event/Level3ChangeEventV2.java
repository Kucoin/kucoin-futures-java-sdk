/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.websocket.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kucoin.futures.core.model.Level3Message;
import lombok.Data;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/7/1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Level3ChangeEventV2 extends Level3Message {
}
