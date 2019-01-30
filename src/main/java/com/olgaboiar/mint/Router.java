package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.FileHandler;
import com.olgaboiar.mint.handlers.IHandler;
import com.olgaboiar.mint.handlers.NotFoundHandler;
import com.olgaboiar.mint.handlers.RouteHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<String, IHandler> routes = new HashMap<>();

    public Router() {
        registerTestRoutes();
    }

    public Response route(Request request) throws IOException {
        IHandler handler =  routes.getOrDefault(request.getRequestedFile(), new NotFoundHandler());
        return handler.handleRequest(request);
    }

    public void setHandler(String route, IHandler routeHandler) {
        routes.put(route, routeHandler);
    }

    public void registerTestRoutes() {
        setHandler("/simple_get", new RouteHandler());
        setHandler("/method_options", new RouteHandler());
        setHandler("/get_with_body", new RouteHandler());
        setHandler("/index.html", new FileHandler());
    }
}
