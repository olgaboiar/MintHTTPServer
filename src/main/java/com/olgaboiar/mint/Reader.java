package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    BufferedReader in;

    public Reader(BufferedReader in) {
        this.in = in;
    }

    public List<String> readInput() throws IOException {
        List<String> clientInputHeaders = new ArrayList<String>();
        String input = in.readLine();
        while (input != null && input.length() > 0) {
            clientInputHeaders.add(input);
            input = in.readLine();
        }
        return clientInputHeaders;
    }

    public String readChars(int contentLength) throws IOException {
        char[] buffer = new char[contentLength];
        in.read(buffer, 0, contentLength);
        String clientInputBody = new String(buffer, 0, buffer.length);
        return clientInputBody;
    }
}
