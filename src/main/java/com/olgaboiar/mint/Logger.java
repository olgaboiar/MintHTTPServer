package com.olgaboiar.mint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Logger {

    public void logToConsole(String message) {
        System.out.println(message);
    }
    
    public void logToFile(String message, String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(fileName, true);
        output.write(message.getBytes());
        output.write("\r\n".getBytes());
        output.close();
    }
}
