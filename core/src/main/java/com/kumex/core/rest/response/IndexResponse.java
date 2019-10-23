/*
 * Copyright 2019 Mek Global Limited.
 */

package com.kumex.core.rest.response;

import com.kumex.core.model.IndexItem;
import lombok.Data;

import java.util.List;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/10/15
 */
@Data
public class IndexResponse extends IndexRateResponse {

    private List<IndexItem> decomposionList;

}
