package io.motaz;

import kacst.lib.KACSTLib;

import java.io.File;
import java.io.IOException;


/*
Arabic normalizer is inspired from
https://stackoverflow.com/questions/20757780/how-to-perform-search-on-arabic-text-in-java
http://ibbtek.altervista.org/index.html

 */

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("hello :)");
        //System.out.println(phonotise("الْخَيْمَةِ"));
        //System.out.println(phonotise("التُّرَابِ"));

        KACSTLib.loadConfig();
        KACSTLib.importFiles(new File("dir"), "utf-8");
        KACSTLib.writeDict(new File("out.dict"), "utf-8");


        System.out.println("done");
    }



}
