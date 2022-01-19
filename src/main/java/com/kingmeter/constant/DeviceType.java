package com.kingmeter.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * description:  device typ enum
 * author: crazyandy <br>
 */
public enum DeviceType {

    SingleVehicle(1),//bike installed with smartlock(only bluetooth,no grps)
    BatteryVehicle(2),//bike installed with smartlock(both bluetooth and grps)
    ScooterVehicle(3),//scooter
    IronSiteType(4),//iron wifimaster
    ChargingSite(5),//charging wifimaster
    GprsLockType(6),//smartlock(both bluetooth and gprs)
    BlueToothLockType(7),//smartlock(only bluetooth)
    HotShoeType(8);//shoe with heat module (bluetooth)

    private int value;

    DeviceType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<Integer, DeviceType> enumMap = new HashMap();

    static {
        for (DeviceType type : DeviceType.values()) {
            enumMap.put(type.value(), type);
        }
    }

    public static DeviceType getEnum(int value) {
        return enumMap.get(value);
    }

    public static boolean containsValue(int value) {
        return enumMap.containsKey(value);
    }
}
