package io.motaz;

import kacst.lib.MyKACSTLib;

import java.io.File;

public class IPADictUtil {
    public static String updateDict(File newEntries, File inDict, File outDict, String encoding) {
        String log = "";
        MyKACSTLib.clearDict();
        if (inDict != null) {
            log += MyKACSTLib.readDict(inDict, encoding);
        }
        log += MyKACSTLib.parseFile(newEntries, encoding);
        log += MyKACSTLib.writeDict(outDict, encoding);
        MyKACSTLib.clearDict();

        return log;
    }
}
