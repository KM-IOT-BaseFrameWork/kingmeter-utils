package com.kingmeter.utils;


public class GpsUtils {

    private static GpsUtils instance;
    private GpsUtils(){};
    public static GpsUtils getInstance(){
        if(instance == null){
            synchronized(GpsUtils.class){
                if(instance == null){
                    instance = new GpsUtils();
                }
            }
        }
        return instance;
    }

    private double EARTH_RADIUS = 6378.137;

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * get distance  according latitude and longitude
     * unit meter
     * @param beginLatitude
     * @param beginLongitude
     * @param endLatitude
     * @param endLongitude
     * @return
     */
    public double getDistance(double beginLatitude, double beginLongitude,
                              double endLatitude,double endLongitude) {
        double radLat1 = rad(beginLatitude);
        double radLat2 = rad(endLatitude);
        double a = radLat1 - radLat2;
        double b = rad(beginLongitude) - rad(endLongitude);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    /**
     * judge whether this position belong to this circle area
     */
    public boolean isInCircle(double beginLatitude, double beginLongitude,
                              double endLatitude, double endLongitude,  double radius) {
        double distance = getDistance(beginLatitude, beginLongitude, endLatitude, endLongitude);
        if (distance > radius) {
            return false;
        } else {
            return true;
        }
    }


    public double calculateGps(String gps) {
        if (gps == null || gps.equals("")) {
            return 0;
        }
        String head = gps.substring(0, 1);//get prefix N/S/W/E
        String first = gps.substring(1, gps.indexOf(".") - 2);//E.G., N2446.79096，get 24
        String last = gps.substring(gps.indexOf(".") - 2);//E.G.: 46.79096

        double lastDouble = Double.valueOf(((Double.parseDouble(last) / 60.0) * 10000000)) / 10000000.0;
        return changeHead(head) * (Integer.parseInt(first) + lastDouble);
    }


//    public static void main(String[] args) {
//        System.out.println(GpsUtils.getInstance().calculateGps("W07642.51577"));
//        System.out.println(GpsUtils.getInstance().calculateGps("N6645.80423"));
//    }

    private int changeHead(String head) {
        switch (head) {
            case "N":
                return 1;
            case "E":
                return 1;
            default:
                return -1;
        }
    }
}
