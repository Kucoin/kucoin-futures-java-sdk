/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.websocket;

import com.kucoin.futures.core.model.InstanceServer;

import java.util.List;
import java.util.Random;

/**
 * Created by chenshiwei on 2019/1/24.
 */
public class RandomChooseStrategy implements ChooseServerStrategy {

    private final Random random = new Random();

    @Override
    public InstanceServer choose(List<InstanceServer> servers) {
        return servers.get(random.nextInt(servers.size()));
    }

}
