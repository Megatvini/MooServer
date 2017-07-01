package ge.mooserver.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

    int pnpoly(int nvert, float *vertx, float *verty, float testx, float testy)
    {
        int i, j, c = 0;
        for (i = 0, j = nvert-1; i < nvert; j = i++) {



        }
        return c;
    }

    boolean pnpoly(List<Coordinat> coordinats, Coordinat cord)
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