package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

public enum SiteStatusType {
    Normal(0),//
    Forbidden(1),//
    Malfuntion(2);//

    private int value;

    SiteStatusType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, SiteStatusType> enumMap = new HashMap();

    static {
        for (SiteStatusType type : SiteStatusType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static SiteStatusType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumMap.containsKey(value);
    }
}
