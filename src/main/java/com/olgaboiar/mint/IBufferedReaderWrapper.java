package com.olgaboiar.mint;

import java.io.IOException;

public interface IBufferedReaderWrapper {
    String readLine() throws IOException;
    void read(char[] buffer, int contentLength) throws IOException;
    void close() throws IOException;
}
