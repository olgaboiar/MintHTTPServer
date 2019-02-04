package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.IHandler;

import java.util.Map;

public final class Route {
    private final String path;
    private final Map<String, IHandler> allowedMethods;

    public Route(String path, Map<String, IHandler> allowedMethods) {
        this.path = path;
        this.allowedMethods = allowedMethods;
    }

    public String getPath() {
        return path;
    }

    public Map<String, IHandler> getAllowedMethods() {
        return allowedMethods;
    }
}
