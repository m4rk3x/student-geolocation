package com.duocode.studentregistration.geolocation;

/**
 * Created by m4rk1n0 on 11/29/20
 **/
public class Coordinates {

    private double latitude;
    private double longitude;

    /**
     * Initialize default coordinates with X and Y values
     */
    public Coordinates() {
    }

    /**
     * Initialize a list of coordinates with X and Y values
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}