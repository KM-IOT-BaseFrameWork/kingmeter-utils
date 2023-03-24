package com.kingmeter.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static DateUtils instance;
    private DateUtils(){};
    public static synchronized DateUtils getInstance(){
        if(instance == null){
            synchronized(DateUtils.class){
                if(instance == null){
                    instance = new DateUtils();
                }
            }
        }
        return instance;
    }
    public long countCyclingTimeByDown(Date startTime,Date endTime){
        long cyclingTime = 0;
        if(endTime!=null&&startTime!=null){
            cyclingTime = endTime.getTime() - startTime.getTime();
            cyclingTime = cyclingTime/60000;
        }
        return cyclingTime;
    }
    public long countCyclingTimeByMinUp(Date startTime,Date endTime){
        if(endTime!=null&&startTime!=null){
            long cyclingTime = endTime.getTime() - startTime.getTime();
            if(cyclingTime<60000){
                return 1;
            }else{
                if(cyclingTime%60000==0){
                    return cyclingTime/60000;
                }else{
                    return cyclingTime/60000 +1;
                }
            }
        }
        return 0;
    }

    public long countCyclingTimeBySecond(Date startTime,Date endTime){
        long cyclingTime = 0;
        if(endTime!=null&&startTime!=null){
            cyclingTime = endTime.getTime() - startTime.getTime();
            if(cyclingTime<1000){
                cyclingTime = 1;
            }else{
                if(cyclingTime%1000==0){
                    cyclingTime = cyclingTime/1000;
                }else{
                    cyclingTime = cyclingTime/1000 +1;
                }
            }
        }
        return cyclingTime;
    }

    /**
     * get timestamp at current system timezone
     * @return
     */
    public long getCurrentTime(Date now) {
        Calendar cal = Calendar.getInstance();
        int offset = cal.get(Calendar.ZONE_OFFSET);
        cal.add(Calendar.MILLISECOND, -offset);
        Long timeStampUTC = cal.getTimeInMillis();
        Long timeStamp = new Date().getTime();
        Long timeZone = (timeStamp - timeStampUTC) / (1000 * 3600);
        return now.getTime() / 1000 + timeZone.intValue() * 3600;
    }
}
