package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

public enum DockType {
    SingleIronType(1),
    ChargeType(2),
    VirtualType(3);//virtual

    private int value;

    DockType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, DockType> enumMap = new HashMap();

    static {
        for (DockType type : DockType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static DockType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumMap.containsKey(value);
    }
}
