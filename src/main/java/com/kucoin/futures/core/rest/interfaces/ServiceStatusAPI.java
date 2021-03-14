/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.interfaces;

import com.kucoin.futures.core.rest.response.ServiceStatusResponse;

import java.io.IOException;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/28
 */
public interface ServiceStatusAPI {

    /**
     * Get the service status.
     *
     * @return
     */
    ServiceStatusResponse getServiceStatus() throws IOException;

}
