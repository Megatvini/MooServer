package ge.mooserver.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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
}