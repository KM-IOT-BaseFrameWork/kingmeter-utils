package com.kingmeter.utils;

import com.kingmeter.constant.*;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HardWareUtils {
    private static HardWareUtils instance;
    private HardWareUtils(){}
    public static HardWareUtils getInstance() {
        if (instance == null) {
            synchronized (HardWareUtils.class) {
                if (instance == null) {
                    instance = new HardWareUtils();
                }
            }
        }
        return instance;
    }
    /**
     * get device type by lock number
     * @param lockId
     * @return
     */
    public int validateLockId(String lockId, String gprpLockPrefix,
                              String bluetoothLockPrefix) {
        String[] gprsArray = gprpLockPrefix.split(",");
        for (String gprsPrex : gprsArray) {
            if (lockId.indexOf(gprsPrex) == 0) {
                return LockType.GprsType.value();
            }
        }
        String[] bluetoothArray = bluetoothLockPrefix.split(",");
        for (String bluetoothPrex : bluetoothArray) {
            if (lockId.indexOf(bluetoothPrex) == 0) {
                return LockType.BluetoothType.value();
            }
        }
        return 0;
    }


    public int validateAllCode(String code, String gprpLockPrefix,
                               String bluetoothLockPrefix,
                               String chargingPilePrefix,
                               String ironPilePrefix) {
        String[] gprsArray = gprpLockPrefix.split(",");
        for (String gprsPrex : gprsArray) {
            if (code.startsWith(gprsPrex)) {
                return DeviceType.GprsLockType.value();
            }
        }
        String[] bluetoothArray = bluetoothLockPrefix.split(",");
        for (String bluetoothPrex : bluetoothArray) {
            if (code.startsWith(bluetoothPrex)) {
                return DeviceType.BlueToothLockType.value();
            }
        }
        String[] ironPileArray = chargingPilePrefix.split(",");
        for (String ironPrex : ironPileArray) {
            if (code.startsWith(ironPrex)) {
                return DeviceType.ChargingSite.value();
            }
        }
        String[] chargingPileArray = ironPilePrefix.split(",");
        for (String chargingPrex : chargingPileArray) {
            if (code.startsWith(chargingPrex)) {
                return DeviceType.IronSiteType.value();
            }
        }
        return 0;
    }

    /**
     * get timestamp
     * @return
     */
    public long getCurrentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * get utc time according the timezone
     * @param timeZone
     * @return
     */
    public long getUtcTimeStampOnDevice(int timeZone) {
        return System.currentTimeMillis() / 1000 + timeZone * 3600;
    }

    /**
     * 根据从设备传来的时间戳，换算成该设备所在时区的当地时间
     * 举例：
     * 在东八区
     * 当前设备显示的时间是 早 8：00 ，其实设备使用的是utc的时间戳，这个时间戳对应本地时间应该是 16:00
     * 所以打印日志，要减去8小时（东区-，西区+）
     *
     * get the local time according the timestamp from the device
     * @param timeZone timezone where the device is located
     * @param time     timestamp uploaded from the device
     * @return
     */
    public String getLocalTimeByHardWareTimeStamp(int timeZone, long time) {
        Date now = new Date(time * 1000 - timeZone * 60 * 60 * 1000);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(now);
    }

    public long getLocalTimeStampByHardWareUtcTimeStamp(int timeZone, long time){
        return time - timeZone * 3600;
    }

    public String getLocalTimeByHardWareTimeStamp(long time){
        Date now = new Date(time * 1000);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(now);
    }


    public long getHostTimeZone() {
        Calendar cal = Calendar.getInstance();
        int offset = cal.get(Calendar.ZONE_OFFSET);
        cal.add(Calendar.MILLISECOND, -offset);
        Long timeStampUTC = cal.getTimeInMillis();
        Long timeStamp = new Date().getTime();
        return (timeStamp - timeStampUTC) / (1000 * 3600);
    }


    public int getSiteTypeBySiteId(String siteId) {
        if (siteId.startsWith("94")) {
            return SiteType.SingleIronType.value();
        } else if (siteId.startsWith("91")) {
            return SiteType.ChargeType.value();
        } else {
            return SiteType.CommonType.value();
        }
    }

    public int getPileTypeByPileCode(String pileCode) {
        if (pileCode.startsWith("v1")) {
            return DockType.SingleIronType.value();
        } else if (pileCode.startsWith("11")) {
            return DockType.ChargeType.value();
        } else if (pileCode.startsWith("70")) {
            return DockType.VirtualType.value();
        }
        return 0;
    }

    public int getVehicleTypeByVehicleId(String vehicleId) {
        if (vehicleId.startsWith("27")) {
            return VehicleType.SingleType.value();
        } else if (vehicleId.startsWith("21")) {
            return VehicleType.ChargeType.value();
        }
        return 0;
    }

    public int getLockTypeByLockId(String lockId) {
        if (lockId.startsWith("86")) {
            return LockType.GprsType.value();
        } else if (lockId.startsWith("87")) {
            return LockType.BluetoothType.value();
        }
        return 0;
    }


    public String correctCardNumber(String cardNumber) {
        if (StringUtils.isEmpty(cardNumber)) {
            return "";
        } else {
            int numberLength = cardNumber.length();
            if (numberLength < 8) {
                StringBuffer sb = new StringBuffer();
                while (numberLength < 8) {
                    sb.append("0");
                    numberLength++;
                }
                sb.append(cardNumber);
                return sb.toString();
            }
            return cardNumber;
        }
    }


    public long getLocalTimeStampByHardWareTimeStamp(int timeZone, long time){
        return time * 1000 - timeZone * 60 * 60 * 1000;
    }




}
