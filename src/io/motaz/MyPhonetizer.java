package io.motaz;

import com.ibbtek.utilities.ArabicNormalizer;
import kacst.lib.ConfigManager;
import kacst.lib.MyKACSTLib;
import kacst.lib.PhoneticDictionaryEntry;

import java.util.ArrayList;
import java.util.Map;

public class MyPhonetizer {
    private static Map<String, PhoneticDictionaryEntry> dict;
    String word;
    ArrayList<String> pronunciations;

    public MyPhonetizer(String word) {
        MyKACSTLib.loadConfig();
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
        this.word = word;
        PhoneticDictionaryEntry e = new PhoneticDictionaryEntry(word);
        if (e.isValid()) {
            e.generateDefs();
        }
        this.pronunciations = e.getDefs();
        ConfigManager.setProperty("Dictionary", dict);
        MyKACSTLib.clearDict();
    }

    public String getPhones() {
        int count = 0;
        String result = "";
        String plainWord = new ArabicNormalizer(this.word).getOutput();
        for (String def : this.pronunciations) {
            count++;
            result = result + plainWord + ((count == 1) ? "" : "(" + count + ")") + "\t\t" + def + "\n";
        }
        return result;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getPronunciations() {
        return pronunciations;
    }

}
