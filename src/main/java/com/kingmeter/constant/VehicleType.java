package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

public enum VehicleType {
    SingleType(1),
    ChargeType(2),
    ScooterType(3);

    private int value;

    VehicleType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, VehicleType> enumMap = new HashMap();

    static {
        for (VehicleType type : VehicleType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static VehicleType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumMap.containsKey(value);
    }
}
