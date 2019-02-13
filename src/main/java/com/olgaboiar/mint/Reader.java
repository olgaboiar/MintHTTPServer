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

    public List<String> readClientInputHeaders() throws IOException {
        List<String> clientInput = new ArrayList<String>();
        String input = in.readLine();
        while (input.length() > 0) {
            clientInput.add(input);
            input = in.readLine();
        }
        return clientInput;
    }

    public String readClientInputBody(int contentLength) throws IOException {
        char[] buffer = new char[contentLength];
        in.read(buffer, 0, contentLength);
        String postData = new String(buffer, 0, buffer.length);
        return postData;
    }
}
