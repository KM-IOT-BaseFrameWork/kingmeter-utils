package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

public enum SiteType {
    SingleIronType(1),
    ChargeType(2),
    CommonType(3);

    private int value;

    SiteType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, SiteType> enumMap = new HashMap();

    static {
        for (SiteType type : SiteType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static SiteType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumMap.containsKey(value);
    }
}
