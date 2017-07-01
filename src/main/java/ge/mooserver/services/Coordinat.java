package ge.mooserver.services;

/**
 * Created by gvara on 7/1/2017.
 */
public class Coordinat {
    private double lat = 0;
    private double lon = 0;

    public Coordinat(double lat, double lon) {
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
