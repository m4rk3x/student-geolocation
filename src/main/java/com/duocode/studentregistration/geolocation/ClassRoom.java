package com.duocode.studentregistration.geolocation;

/**
 * Created by m4rk1n0 on 11/29/20
 **/
public class ClassRoom {

    private String name;
    private double latitude;
    private double longitude;
    private double length;

    public ClassRoom(String name, double latitude, double longitude, double length) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", length=" + length +
                '}';
    }
}
