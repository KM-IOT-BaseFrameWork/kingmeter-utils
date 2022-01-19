package com.kingmeter.constant;

import com.kingmeter.common.KingMeterException;
import com.kingmeter.common.ResponseCode;
import com.kingmeter.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

public enum OTAIronType {

    BDST(1),//Terminal(WIFImaster)
    BDSF(7),//BDS-F dock
    BDSD(9);//BDS-D

    private int value;

    OTAIronType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    static Map<String, OTAIronType> enumMap = new HashMap();

    static Map<Integer, OTAIronType> enumValueMap = new HashMap();

    static {
        for (OTAIronType type : OTAIronType.values()) {
            enumMap.put(type.name(), type);
            enumValueMap.put(type.value, type);
        }
    }

    public static boolean containsName(String name) {
        return enumMap.containsKey(name);
    }
    public static OTAIronType getEnumByName(String name) {
        return enumMap.get(name);
    }

    public static OTAIronType getEnumByValue(int value) {
        return enumValueMap.get(value);
    }
    public static boolean containsValue(Integer value) {
        return enumValueMap.containsKey(value);
    }


    public void validateFileName(OTAIronType type, String name){
        Map<OTAIronType,String> map = new HashMap<>();
        map.put(BDST,"BDS_T_");
        map.put(BDSF,"BDS_F");
        map.put(BDSD,"BDS_D_");

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
