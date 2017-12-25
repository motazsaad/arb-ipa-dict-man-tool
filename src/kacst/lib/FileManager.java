package kacst.lib;

import java.io.InputStream;

public class FileManager {
    public static String conf = "/kacst/conf/";

    public static InputStream read(String file) {
        // try{
        return FileManager.class.getResourceAsStream(conf + file);
        //}catch(URISyntaxException e){return null;}
    }
}