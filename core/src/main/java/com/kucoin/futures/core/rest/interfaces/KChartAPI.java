/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.futures.core.rest.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * @author chenshiwei
 * @email casocroz@gmail.com
 * @date 2020/6/28
 */
public interface KChartAPI {

    /**
     * Get K Line Data of Contract
     *
     * @param symbol        [Required]Symbol
     * @param granularity   [Required]Granularity (minute)
     * @param from          [Optional]Start time (milisecond)
     * @param to            [Optional]End time (milisecond)
     * @return
     * [
     *  [
     *      1575331200000,//Time
     *      7495.01,      //Entry price
     *      8309.67,      //Highest price
     *      7250,         //Lowest price
     *      7463.55,      //Close price
     *      0             //Trading volume
     *  ],
     *  [
     *      1575374400000,
     *      7464.37,
     *      8297.85,
     *      7273.02,
     *      7491.44,
     *      0
     *   ]
     * ]
     */
    List<List<String>> getKChart(String symbol, int granularity, Long from, Long to) throws IOException;

}
