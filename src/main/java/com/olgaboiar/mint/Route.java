package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.IHandler;

import java.util.Map;

public final class Route {
    private final String path;
    Map<String, IHandler> methodHandlers;

    public Route(String path, Map<String, IHandler> methodHandlers) {
        this.path = path;
        this.methodHandlers = methodHandlers;
    }

    public String getPath() {
        return path;
    }

    public Map<String, IHandler> getAllowedMethods() {
        return methodHandlers;
    }

}
