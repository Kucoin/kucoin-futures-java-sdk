/*
 * Copyright 2019 Mek Global Limited
 */

package com.kumex.core.rest.interfaces;

import com.kumex.core.rest.response.ServiceStatusResponse;

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
