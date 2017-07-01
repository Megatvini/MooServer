package ge.mooserver.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Nika Doghonadze
 */
public class Utils {
    public static File getFileFromResources(String fileName) throws URISyntaxException, IOException {
       File f =  new File(fileName);
        if (!f.exists()){
            f.createNewFile();
        }
        return f;
    }

    boolean pnpoly(List<Coordinate> coordinats, Coordinate cord) {
        int i, j = 0;
        boolean c = false;
        double testy = cord.getLat();
        double testx = cord.getLon();
        for (i = 0, j = coordinats.size() - 1; i < coordinats.size(); j = i++) {
            double lonyi = coordinats.get(i).getLat();
            double lonxi = coordinats.get(i).getLon();
            double lonyj = coordinats.get(j).getLat();
            double lonxj = coordinats.get(j).getLon();


            if ( ((lonyi>testy) != (lonyj>testy)) &&
                    (testx < (lonxj-lonxi) * (testy-lonyi) / (lonyj-lonyi) + lonxi) ){
                c = !c;
            }

        }
        return c;
    }

    public static boolean isConvex(List<Coordinate> coordinats)
    {
        if (coordinats.size() < 4)
            return true;
        boolean sign = false;
        int n = coordinats.size();
        for(int i=0; i<n; i++)
        {
            double dx1 = coordinats.get((i+2)%n).getLon()-coordinats.get((i+1)%n).getLon();
            double dy1 = coordinats.get((i+2)%n).getLat()-coordinats.get((i+1)%n).getLat();
            double dx2 = coordinats.get(i).getLon()-coordinats.get((i+1)%n).getLon();
            double dy2 = coordinats.get(i).getLat()-coordinats.get((i+1)%n).getLat();
            double zcrossproduct = dx1*dy2 - dy1*dx2;
            if (i == 0)
                sign = zcrossproduct > 0;
            else if (sign != (zcrossproduct > 0))
                return false;
        }
        return true;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {
        double el1 = 0;
        double el2 = 0;

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}