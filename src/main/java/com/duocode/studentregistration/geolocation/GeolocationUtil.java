package com.duocode.studentregistration.geolocation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by m4rk1n0 on 11/29/20
 **/
public class GeolocationUtil {

    final static double DOUBLE_ONE_HALF = 2D;
    final static double R_EARTH = 6371000.0;

    public enum Operators {
        ADD,
        SUBTRACT
    }

    /**
     *
     * @param p1: First coordinate of the square (lowest possible)
     * @param p2: Second coordinate of the square (greatest possible)
     * @param studentLoc: Student coordinate
     * @return
     */
    public boolean isBounded(Coordinates p1, Coordinates p2, Coordinates studentLoc) {
        if(p1.getLatitude() <= studentLoc.getLatitude() && studentLoc.getLatitude() <= p2.getLatitude() &&
                p1.getLongitude() <= studentLoc.getLongitude() && studentLoc.getLongitude() <= p2.getLongitude()) {
            return true;
        }
        return false;
    }

    public List<Coordinates> findSquareCoordinateFromACenterPoint(double length, double c_x, double c_y) {
        List<Coordinates> result = new ArrayList<>(2);
        Coordinates point1 = new Coordinates();
        point1.setLatitude(c_x - length/DOUBLE_ONE_HALF);
        point1.setLongitude(c_y - length/DOUBLE_ONE_HALF);
        Coordinates point2 = new Coordinates();
        point2.setLatitude(c_x + length/DOUBLE_ONE_HALF);
        point2.setLongitude(c_y + length/DOUBLE_ONE_HALF);
        result.add(point1);
        result.add(point2);
        return result;
    }

    public List<Student> studentsInClasses (List<Student> studentList, List<ClassRoom> classRoomList) {
        List<Student> resultList = new ArrayList<>();
        List<Coordinates> coordinates = new ArrayList<>(2);
        if (studentList.size() > 0 && classRoomList.size() > 0) {
            for(Student student : studentList) {
                for(ClassRoom classRoom : classRoomList){
                    coordinates = findSquareCoordinateFromACenterPoint(classRoom.getLength(), classRoom.getLatitude(), classRoom.getLongitude());
                    Coordinates studentLoc = new Coordinates(student.getLatitude(), student.getLongitude());
                    Coordinates point1 = coordinates.get(0);
                    Coordinates point2 = coordinates.get(1);
                    if (isBounded(point1, point2, studentLoc)) {
                        resultList.add(student);
                    }
                }
            }
            return resultList.stream()
                    .distinct()
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     *         The number of kilometers per degree of longitude is approximately (2*pi/360) * r_earth * cos(theta)
     *         where theta is the latitude in degrees and r_earth is approximately 6378 km.
     *         The number of kilometers per degree of latitude is approximately the same at all locations, approx
     *         (2*pi/360) * r_earth = 111 km / degree
     *         As long as distance_x and distance_y (in this case sample is the same value) are small compared to the radius of the earth and you don't get too close to the poles.
     *
     * @param current: It is the coordinates we want to modify by adding or substract
     * @param distance: expressed in meters
     * @param operation: This can be Operators.ADD or Operators.SUBSTRACT
     * @return: This method returns new calculated end point
     */
    public Coordinates calcNewEndPoint(Coordinates current, int distance, Operators operation) {
        double doubleDistance = Double.valueOf(distance);
        Coordinates newCoordinates = new Coordinates();
        switch(operation) {
            case ADD:
                newCoordinates.setLatitude(current.getLatitude() + (doubleDistance/R_EARTH) * (180/Math.PI));
                newCoordinates.setLongitude(current.getLongitude() + (doubleDistance/R_EARTH) * (180/Math.PI)/Math.cos(current.getLatitude() * Math.PI/180));
                break;
            case SUBTRACT:
                newCoordinates.setLatitude(current.getLatitude() - (doubleDistance/R_EARTH) * (180/Math.PI));
                newCoordinates.setLongitude(current.getLongitude() - (doubleDistance/R_EARTH) * (180/Math.PI)/Math.cos(current.getLatitude() * Math.PI/180));
        }
        return newCoordinates;
    }

}
