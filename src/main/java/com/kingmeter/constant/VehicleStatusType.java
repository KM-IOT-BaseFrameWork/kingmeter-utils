package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

public enum VehicleStatusType {
    BikeInit(0),//
    BikeInDock(1),//
    BikeBeingUsed(2),//
    BikeRepairing(3),//
    BikeScrap(4),//
    BikeLost(5);//

    private int value;

    VehicleStatusType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, VehicleStatusType> enumMap = new HashMap();

    static {
        for (VehicleStatusType type : VehicleStatusType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static VehicleStatusType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumMap.containsKey(value);
    }

}
