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



    boolean pnpoly(List<Coordinate> coordinats, Coordinate cord)
    {
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
}