/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.response;

import com.kucoin.futures.core.model.IndexItem;
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
