package ge.mooserver.services;

public class Coordinate {
    private double lat = 0;
    private double lon = 0;

    public Coordinate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
