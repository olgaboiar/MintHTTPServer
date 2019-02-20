package com.olgaboiar.mint;

import java.io.IOException;

public class MockServerSocketWrapper implements IServerSocketWrapper {
    MockSocketWrapper mockSocketWrapper;

    public MockServerSocketWrapper(MockSocketWrapper mockSocketWrapper) {
        this.mockSocketWrapper = mockSocketWrapper;
    }

    @Override
    public ISocketWrapper accept() {
        return mockSocketWrapper;
    }

    @Override
    public void close() throws IOException {
        mockSocketWrapper.close();
    }
}
