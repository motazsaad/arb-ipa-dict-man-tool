package kacst.lib;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KACSTLib {
    //public static StatusEvent statusEvent = new StatusEvent();
    //public static ProgressEvent progressEvent = new ProgressEvent();
    //public static UpdateEvent updateEvent = new UpdateEvent();
    //public static boolean stopThreads = false;
    private static Map<String, PhoneticDictionaryEntry> dict;
    private static Pattern pattern;
    private static int count;

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

    public static void parseFile(File f, String encoding) {
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
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
                        dict.put(s, e);
                    }
                }
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDict(File f, String encoding) {
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
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
                    if (dict.containsKey(s))
                        e = dict.get(s);
                    else
                        dict.put(s, e);
                    e.addDef(def);
                }
                str = reader.readLine();
            }
            //statusEvent.fire("Done, loaded "+dict.size()+" entries");
            System.out.println("Done, loaded " + dict.size() + " entries");
            //updateEvent.fire();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void updateList(){
//        updateEvent.fire();
//    }

    public static void clearDict() {
        dict = new TreeMap<String, PhoneticDictionaryEntry>();
        ConfigManager.setProperty("Dictionary", dict);
    }

    public static void writeDict(File f, String encoding) {
        dict = (Map<String, PhoneticDictionaryEntry>) ConfigManager.getProperty("Dictionary");
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), encoding));
            for (PhoneticDictionaryEntry e : dict.values()) {
                writer.print(e);
            }
            writer.close();
            //JOptionPane.showMessageDialog(null, "Dictionary written successfully!");
            System.out.println("Dictionary written successfully!");
        } catch (IOException excep) {
        }

    }
}
