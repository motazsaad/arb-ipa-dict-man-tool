package kacst.lib;

import com.ibbtek.utilities.ArabicNormalizer;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    This class is created by Motaz Saad. It is a modification to kacst.lib.Main class
    The modification makes the class standalone (separate business logic from gui operations
     */
public class MyKACSTLib {

    // commented by Motaz Saad, these are for gui
    //public static StatusEvent statusEvent = new StatusEvent();
    //public static ProgressEvent progressEvent = new ProgressEvent();
    //public static UpdateEvent updateEvent = new UpdateEvent();
    //public static boolean stopThreads = false;
    private static Map<String, PhoneticDictionaryEntry> dict;
    private static Pattern pattern;
    //private static int count; // no need for it

    public static void importFiles(File dir, String encoding) {
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
        File[] files = dir.listFiles();
        System.out.println("loading files");
        //statusEvent.fire("Loading Files");
        //progressEvent.setMaximum(getNumFiles(dir));
        System.out.printf("Number of files: %d\n", getNumFiles(dir));
        int count = 0;
        for (File f : files) {
            //if(!stopThreads){
            //progressEvent.fire("Loading "+f.getName(),++count);
            System.out.printf("Loading %s %d\n", f.getName(), ++count);
            System.out.println("parsing file" + f.getName());
            parseFile(f, encoding);

            //}
        }
        ConfigManager.setProperty("Dictionary", dict);
        System.out.println("Done");
        //statusEvent.fire("Done");
        //updateEvent.fire();

    }

    public static void loadConfig() {
        Map<String, Character> map = CharacterMap.get();
        ConfigManager.setProperty("CharMap", map);
        Map<String, String> classes = CharacterClasses.get();
        pattern = Pattern.compile("(" + classes.get("D") + "|" + classes.get("L") + ")+");
        ConfigManager.setProperty("Classes", classes);
        Map<String, ArrayList<Rule>> rules = Rules.get();
        ConfigManager.setProperty("Rules", rules);
        dict = new TreeMap<String, PhoneticDictionaryEntry>();
        ConfigManager.setProperty("Dictionary", dict);
    }

    public static int getNumFiles(File dir) {
        return dir.listFiles().length;
    }

    public static String parseFile(File f, String encoding) {
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
        String message = "";
        int wordCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), encoding));
            String str = reader.readLine();
            while (str != null) {
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    String s = matcher.group();
                    //System.out.println("s:" + s);
                    PhoneticDictionaryEntry e = new PhoneticDictionaryEntry(s);
                    if (e.isValid()) {
                        e.generateDefs();
                        String plainWord = new ArabicNormalizer(s).getOutput();
                        //dict.put(s, e);
                        dict.put(plainWord, e);
                        wordCount++;
                    }
                }
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        message += "Entry file " + f.getName() + " loaded (" + wordCount + " words)\n";
        System.out.println(message);
        return message;
    }

    public static String readDict(File f, String encoding) {
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
        String message = "";
        try {
            //statusEvent.fire("Loading Dictionary: "+f.getPath());
            System.out.println("Loading Dictionary: " + f.getPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), encoding));
            String str = reader.readLine();
            Map<String, String> classes = (Map<String, String>) ConfigManager.getProperty("Classes");
            Pattern p = Pattern.compile("^([" + classes.get("D") + classes.get("L") + "]+)(?:\\([0-9]{1,2}\\))?[ \t]+(.*)$");
            int count = 0;
            while (str != null) {
                Matcher matcher = p.matcher(str);
                while (matcher.find()) {
                    String s = matcher.group(1);
                    String def = matcher.group(2);
                    PhoneticDictionaryEntry e = new PhoneticDictionaryEntry(s);
                    String plainWord = new ArabicNormalizer(s).getOutput();
                    if (dict.containsKey(s))
                        e = dict.get(s);
                    else if (dict.containsKey(plainWord))
                        e = dict.get(plainWord);
                    else
                        dict.put(s, e);
                    e.addDef(def);
                }
                str = reader.readLine();
            }
            //statusEvent.fire("Done, loaded "+dict.size()+" entries");
            message += "Dictionary " + f.getName() + " loaded (" + dict.size() + " entries)\n";
            System.out.print(message);
            //updateEvent.fire();
        } catch (IOException e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

//    public static void updateList(){
//        updateEvent.fire();
//    }

    public static void clearDict() {
        dict = new TreeMap<String, PhoneticDictionaryEntry>();
        ConfigManager.setProperty("Dictionary", dict);
    }

    public static String writeDict(File f, String encoding) {
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
        String message = "";
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), encoding));
            for (PhoneticDictionaryEntry e : dict.values()) {
                writer.print(e);
            }
            writer.close();
            //JOptionPane.showMessageDialog(null, "Dictionary written successfully!");
            message += "Dictionary written successfully (" + dict.size() + " entries written)\n";
            System.out.print(message);
        } catch (IOException excep) {
            message = excep.getMessage();
        }
        return message;
    }
}
