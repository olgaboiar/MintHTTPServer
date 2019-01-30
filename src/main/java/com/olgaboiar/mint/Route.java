package com.olgaboiar.mint;

import java.util.Objects;

public final class Route {
    private final String path;
    private final String method;

    public Route(String path, String method) {
        this.path = path;
        this.method = method;
    }

    String getPath() {
        return path;
    }

    String getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object route) {
        if (route == this) {
            return true;
        }
        if (!(route instanceof Route)) {
            return false;
        }
        Route routeClone = (Route) route;
        return Objects.equals(path, routeClone.path) &&
                Objects.equals(method, routeClone.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method);
    }
}
