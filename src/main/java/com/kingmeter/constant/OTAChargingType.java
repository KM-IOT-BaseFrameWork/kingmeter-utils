package com.kingmeter.constant;

import com.kingmeter.common.KingMeterException;
import com.kingmeter.common.ResponseCode;
import com.kingmeter.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

public enum OTAChargingType {

    WDST(1),//Terminal(WIFImaster)
    ICPU(2),//ICPU
    DISPLAY(3),//display
    EICC(4),//EICC
    FONTLIBRARY(5),//fontlibrary
    PCRD(6);//PCRD

    private int value;

    OTAChargingType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<String, OTAChargingType> enumMap = new HashMap();

    static Map<Integer, OTAChargingType> enumValueMap = new HashMap();

    static {
        for (OTAChargingType type : OTAChargingType.values()) {
            enumMap.put(type.name(), type);
            enumValueMap.put(type.value, type);
        }
    }

    public static boolean containsName(String name) {
        return enumMap.containsKey(name);
    }
    public static OTAChargingType getEnumByName(String name) {
        return enumMap.get(name);
    }

    public static OTAChargingType getEnumByValue(int value) {
        return enumValueMap.get(value);
    }
    public static boolean containsValue(Integer value) {
        return enumValueMap.containsKey(value);
    }


    public static void validateFileName(OTAChargingType type, String name){
        Map<OTAChargingType,String> map = new HashMap<>();
        map.put(WDST,"V8152_TERM_B_");
        map.put(ICPU,"V8152_ICPU_B_");
        map.put(DISPLAY,"V8152_DISP_B_");
        map.put(EICC,"V8152_EICC_B_");
        map.put(FONTLIBRARY,"V8152_FONT_B_");
//        map.put(PCRD,"V8152_P_B_");

        if (name.indexOf("/") != -1) {
            name = name.substring(name.lastIndexOf("/") + 1);
        }

        if(map.containsKey(type)){
            String namePrefix = map.get(type);
            if(StringUtil.isNotEmpty(name) && name.startsWith(namePrefix)){
                return;
            }
        }

        throw new KingMeterException(ResponseCode.FirmWareNameWrongFormatException);
    }

}
