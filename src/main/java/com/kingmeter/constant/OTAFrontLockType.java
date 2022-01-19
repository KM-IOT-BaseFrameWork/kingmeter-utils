package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

public enum OTAFrontLockType {

    DP("28"),//28
    MC("EF"),//EF
    BMS("F4"),//F4
    IOT("21");//21

    private String value;

    OTAFrontLockType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    static Map<String, OTAFrontLockType> enumMap = new HashMap();

    static Map<String, OTAFrontLockType> enumValueMap = new HashMap();

    static {
        for (OTAFrontLockType type : OTAFrontLockType.values()) {
            enumMap.put(type.name(), type);
            enumValueMap.put(type.value, type);
        }
    }

    public static OTAFrontLockType getEnumByName(String name) {
        return enumMap.get(name);
    }
    public static OTAFrontLockType getEnumByValue(String value) {
        return enumValueMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumValueMap.containsKey(value);
    }
    public static boolean containsName(String name) {
        return enumMap.containsKey(name);
    }
}
