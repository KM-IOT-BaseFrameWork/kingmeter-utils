package com.kingmeter.utils;


/**
 * validate data from wifimaster
 */
public class CRCUtils {

    private static CRCUtils instance;

    private CRCUtils() {
    }

    ;

    public static CRCUtils getInstance() {
        if (instance == null) {
            synchronized (CRCUtils.class) {
                if (instance == null) {
                    instance = new CRCUtils();
                }
            }
        }
        return instance;
    }

//    public String getCheckCrc(byte[] bytes) {
//        String crcStr = getCheckCrcString(bytes);
//        StringBuffer result = new StringBuffer();
//        result.append(crcStr.substring(0,2)).
//                append(" ").
//                append(crcStr.substring(2,4));
//        return result.toString();
//    }

    public byte[] getCheckCrcArray(byte[] bytes) {
        String crcStr = getCheckCrcString(bytes);
        byte[] result = new byte[2];
        result[0] = (byte) Integer.parseInt(crcStr.substring(0, 2), 16);
        result[1] = (byte) Integer.parseInt(crcStr.substring(2, 4), 16);
        return result;
    }

    private String getCheckCrcString(byte[] bytes) {
        int crc = 0x00; // initial value
        int polynomial = 0x1021;
        for (int index = 0; index < bytes.length; index++) {
            byte b = bytes[index];
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit)
                    crc ^= polynomial;
            }
        }
        crc &= 0xffff;

        return String.format("%04x", crc);
    }

    public boolean validate(byte[] toCheckByteArray, byte crc1, byte crc2) {
        byte[] checkCrcArray = getCheckCrcArray(toCheckByteArray);

        if (checkCrcArray[0] == crc1 && checkCrcArray[1] == crc2) {
            return true;
        }
        return false;
    }

}
