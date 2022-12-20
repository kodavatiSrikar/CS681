package edu.umb.cs681;

import java.util.ArrayList;

public class Position {
	
	private final double lattd, longtd, alttd;

    public Position(double latitude, double longitude, double altitude) {
        this.lattd = latitude;
        this.longtd = longitude;
        this.alttd = altitude;
    }

    public double getLatitude() {
        return lattd;
    }

    public double getLongitude() {
        return longtd;
    }

    public double getAltitude() {
        return longtd;
    }

    public ArrayList<Double> getCoordinate() {
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(lattd);
        coordinates.add(longtd);
        coordinates.add(alttd);
        return coordinates;
    }

    public Position changeLat(double newLat) {
        return new Position(newLat, this.longtd, this.alttd);
    }

    public Position changeLong(double newLong) {
        return new Position(this.lattd, newLong, this.alttd);
    }

    public Position changeAlt(double newAlt) {
        return new Position(this.lattd, this.longtd, newAlt);
    }

    public double distanceTo(Position anotherPosition) {
        double lat1 = this.lattd;
        double lat2 = anotherPosition.lattd;
        double lon1 = this.longtd;
        double lon2 = anotherPosition.longtd;
        return Math.sqrt((lon2 - lon1) * (lon2 - lon1) + (lat2 -lat1) * (lat2 - lat1));
    }

    public String toString() {
        return "(" + lattd + ", " + longtd + ", " + alttd + ")";
    }
    
    
    
    public static void main(String[] args) {
        Position point1 = new Position(40.813, -88.1795, 40);
        Position point2 = new Position(45.813, -84.1875, 40);
        

        System.out.println(point1.distanceTo(point2));

        System.out.println(point1.changeLat(-30.24));
        System.out.println(point1.changeLong(50.352));
        System.out.println(point1.changeAlt(110));
    }

}
