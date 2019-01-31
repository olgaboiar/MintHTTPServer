package com.olgaboiar.mint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileLogger implements ILogger {
    String fileName;

    public FileLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void logMessage(String message) throws IOException {
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
