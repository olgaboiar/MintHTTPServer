package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface ISocketWrapper {
    BufferedReader getInputStream() throws IOException;
    PrintWriter getPrintWriter() throws IOException;
    void close() throws IOException;
}
