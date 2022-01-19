package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

public enum SiteIsOnlineType {
    OnLine(0),//
    OffLine(1);//

    private int value;

    SiteIsOnlineType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, SiteIsOnlineType> enumMap = new HashMap();

    static {
        for (SiteIsOnlineType type : SiteIsOnlineType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static SiteIsOnlineType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumMap.containsKey(value);
    }
}
