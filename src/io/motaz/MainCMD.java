package io.motaz;

import kacst.lib.MyKACSTLib;

import java.io.File;
import java.io.IOException;


/*
Arabic normalizer is inspired from
https://stackoverflow.com/questions/20757780/how-to-perform-search-on-arabic-text-in-java
http://ibbtek.altervista.org/index.html

 */

public class MainCMD {

    public static void main(String[] args) throws IOException {
        MyKACSTLib.loadConfig();

        IPADictUtil.updateDict(new File("dir/words1.txt"), new File("out1.dict"), new File("out2.dict"), "utf-8");
        IPADictUtil.updateDict(new File("dir/words2.txt"), new File("out1.dict"), new File("out3.dict"), "utf-8");

        System.out.println("done");
    }


}
