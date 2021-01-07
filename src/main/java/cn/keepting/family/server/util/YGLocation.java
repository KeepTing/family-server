package cn.keepting.family.server.util;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public class YGLocation {
    private double longitude;
    private double latitude;

    public YGLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
