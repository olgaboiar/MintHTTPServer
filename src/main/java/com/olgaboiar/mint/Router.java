package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<Route, IHandler> routes = new HashMap<>();

    public Router() {
        registerTestRoutes();
    }

    public Response route(Request request) throws IOException {
        Route route = new Route(request.getRequestedFile(), request.getMethod());
        IHandler handler =  routes.getOrDefault(route, new NotFoundHandler());
        return handler.handleRequest(request);
    }

    public void setHandler(Route route, IHandler routeHandler) {
        routes.put(route, routeHandler);
    }

    public void registerTestRoutes() {
        setHandler(new Route("/simple_get", "GET"), new RouteHandler());
        setHandler(new Route("/simple_get", "HEAD"), new HeadHandler());
        setHandler(new Route("/method_options", "GET"), new RouteHandler());
        setHandler(new Route("/get_with_body", "GET"), new RouteHandler());
        setHandler(new Route("/get_with_body", "HEAD"), new HeadHandler());
        setHandler(new Route("/index.html", "GET"), new FileHandler());
        setHandler(new Route("/index.html", "HEAD"), new HeadHandler());
    }
}
