package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * type of smart lock
 */
public enum LockType {

    GprsType(1),//grps and bluetooth
    BluetoothType(2);//only bluetooth


    private int value;

    LockType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, LockType> enumMap = new HashMap();

    static {
        for (LockType type : LockType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static LockType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(Integer value) {
        return enumMap.containsKey(value);
    }

}
