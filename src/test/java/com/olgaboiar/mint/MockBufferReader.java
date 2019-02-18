package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class MockBufferReader implements IBufferedReaderWrapper {
    BufferedReader in;

    public MockBufferReader(String input) {
        StringReader stringReader = new StringReader(input);
        in = new BufferedReader(stringReader);
    }

    @Override
    public String readLine() throws IOException {
        return in.readLine();
    }

    @Override
    public void read(char[] buffer, int contentLength) throws IOException {
        in.read(buffer, 0, contentLength);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
