package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class MockSocketWrapper implements ISocketWrapper {
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public MockSocketWrapper(PrintWriter printWriter, BufferedReader bufferedReader) {
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public BufferedReader getInputStream() {
        return bufferedReader;
    }

    @Override
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    @Override
    public void close() {

    }
}
