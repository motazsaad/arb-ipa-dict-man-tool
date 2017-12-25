package io.motaz;

import kacst.lib.KACSTMain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("hello :)");
        String content = new String(Files.readAllBytes(Paths.get("dir/words1.txt")));
        System.out.println(content);
        String words[] = content.split(" ");
        KACSTMain.loadConfig();
        KACSTMain.importFiles(new File("dir"), "utf-8");
        KACSTMain.writeDict(new File("out.dict"), "utf-8");
        System.out.println("done");

    }
}
