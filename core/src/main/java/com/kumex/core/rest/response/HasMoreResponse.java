package com.kumex.core.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2019/7/26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HasMoreResponse<T> {

    private boolean hasMore;

    private List<T> dataList;

}
