package io.motaz;

import kacst.lib.MyKACSTLib;

import java.io.File;
import java.io.IOException;


/*
Arabic normalizer is inspired from
https://stackoverflow.com/questions/20757780/how-to-perform-search-on-arabic-text-in-java
http://ibbtek.altervista.org/index.html

 */

public class Main {

    public static void main(String[] args) throws IOException {
        MyKACSTLib.loadConfig();

        updateDict(new File("out1.dict"), new File("dir/words1.txt"), new File("out2.dict"), "utf-8");
        updateDict(new File("out1.dict"), new File("dir/words2.txt"), new File("out3.dict"), "utf-8");

        System.out.println("done");
    }

    public static void updateDict(File inDict, File newEntries, File outDict, String encoding) {
        MyKACSTLib.clearDict();
        MyKACSTLib.readDict(inDict, encoding);
        MyKACSTLib.parseFile(newEntries, encoding);
        MyKACSTLib.writeDict(outDict, encoding);
        MyKACSTLib.clearDict();
    }

}
