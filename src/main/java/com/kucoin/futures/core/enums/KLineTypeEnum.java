package com.kucoin.futures.core.enums;

import lombok.Getter;

@Getter
public enum KLineTypeEnum {

    ONE_MIN("1min"),
    THREE_MIN("3min"),
    FIVE_MIN("5min"),
    FIFTEEN_MIN("15min"),
    THIRTY_MIN("30min"),
    ONE_HOUR("1hour"),
    TWO_HOUR("2hour"),
    FOUR_HOUR("4hour"),
    EIGHT_HOUR("8hour"),
    TWELVE_HOUR("12hour"),
    ONE_DAY("1day"),
    ONE_WEEK("1week"),
    ONE_MONTH("1month");

    KLineTypeEnum(String type) {
        this.type = type;
    }

    private final String type;

    public String kLineParam(String symbol){
        return symbol+"_" + type;
    }

}
