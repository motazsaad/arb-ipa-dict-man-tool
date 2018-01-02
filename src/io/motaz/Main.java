package io.motaz;

import kacst.lib.KACSTLib;
import kacst.lib.PhoneticDictionaryEntry;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("hello :)");
        //String content = new String(Files.readAllBytes(Paths.get("dir/words1.txt")));
        //System.out.println(content);
        //String words[] = content.split(" ");
        //KACSTLib.loadConfig();
        //KACSTLib.importFiles(new File("dir"), "utf-8");
        //KACSTLib.writeDict(new File("out.dict"), "utf-8");

        phonotise("الْخَيْمَةِ");
        System.out.println("done");
    }


    public static ArrayList<String> phonotise(String word) {
        KACSTLib.loadConfig();
        PhoneticDictionaryEntry e = new PhoneticDictionaryEntry(word);
        if (e.isValid()) {
            e.generateDefs();
        }
        ArrayList<String> pronunciation = e.getDefs();
        System.out.println(pronunciation);
        int count = 0;
        String result = "";
        for (String def : pronunciation) {
            count++;
            //result = result + word + "\t\t" + def + "\n";
            result = result + word + ((count == 1) ? "" : "(" + count + ")") + "\t\t" + def + "\n";
        }
        System.out.println("result:");
        System.out.println(result);
        return e.getDefs();
    }
}
