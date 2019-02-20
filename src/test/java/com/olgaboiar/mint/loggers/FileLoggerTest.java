package com.olgaboiar.mint.loggers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class FileLoggerTest {

    @Test
    void testLoggingMessagesToFile() throws IOException {
        String filePath = "artifact-2";
        File file = new File(filePath);
        if (!file.exists()){
            filePath = "testLoggerLogs.txt";
            file = new File(filePath);
        }
        FileLogger testFileLogger = new FileLogger(filePath);
        String message = "test log message";
        testFileLogger.logMessage(message);
        String actual = readLastLine(file);
        System.out.println(actual);
        Assertions.assertEquals(message, actual);
    }

    public String readLastLine(File file) throws IOException {
        RandomAccessFile fileHandler = new RandomAccessFile( file, "r" );
        long fileLength = fileHandler.length() - 1;
        StringBuilder sb = new StringBuilder();

        for(long filePointer = fileLength; filePointer != -1; filePointer--){
            fileHandler.seek( filePointer );
            int readByte = fileHandler.readByte();

            if( readByte == 0xA ) {
                if( filePointer == fileLength ) {
                    continue;
                }
                break;

            } else if( readByte == 0xD ) {
                if( filePointer == fileLength - 1 ) {
                    continue;
                }
                break;
            }
            sb.append( ( char ) readByte );
        }

        String lastLine = sb.reverse().toString();
        return lastLine;
    }
}