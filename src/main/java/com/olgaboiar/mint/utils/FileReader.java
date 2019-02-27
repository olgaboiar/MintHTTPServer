package com.olgaboiar.mint.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {

    public String readFileToString(String filePath) throws IOException {
        InputStream in = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String fileString = new String();
        String line;
        while ((line = reader.readLine()) != null) {
            fileString += line + "\n";
        }
        return fileString;
    }
}
