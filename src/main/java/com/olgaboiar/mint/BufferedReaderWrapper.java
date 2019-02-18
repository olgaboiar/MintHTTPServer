package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;

public class BufferedReaderWrapper implements IBufferedReaderWrapper {
    BufferedReader in;

    public BufferedReaderWrapper(BufferedReader in) {
        this.in = in;
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
