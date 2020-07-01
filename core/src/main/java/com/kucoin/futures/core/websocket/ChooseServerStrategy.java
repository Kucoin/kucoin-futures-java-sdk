/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket;

import java.util.List;

import com.kucoin.futures.core.model.InstanceServer;

/**
 * Created by chenshiwei on 2019/1/24.
 */
public interface ChooseServerStrategy {

    /**
     * For choose web socket connect server
     *
     * @param servers
     * @return A server instance.
     */
    InstanceServer choose(List<InstanceServer> servers);

}
