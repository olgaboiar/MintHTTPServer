package com.olgaboiar.mint;

public final class Route {
    private final String path;
    String[] allowedMethods;

    public Route(String path, String[] allowedMethods) {
        this.path = path;
        this.allowedMethods = allowedMethods;
    }

    public String getPath() {
        return path;
    }

    public String[] getAllowedMethods() {
        return allowedMethods;
    }
}
