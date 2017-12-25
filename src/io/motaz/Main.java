package io.motaz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("hello :)");
        String content = new String(Files.readAllBytes(Paths.get("words.txt")));
        System.out.println(content);
    }
}
