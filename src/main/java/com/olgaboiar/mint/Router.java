package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.*;

public class Router {
    private Map<String, Map<String, IHandler>> routes = new HashMap<>();

    public Router() {
        registerTestRoutes();
    }

    public Response route(Request request) throws IOException {
        Map<String, IHandler> methodList = routes.get(request.getRequestedFile());
        if (methodList == null) {
            return new NotFoundHandler().handleRequest(request);
        }
        Set<String> allowedMethods = methodList.keySet();
        IHandler handler =  methodList.getOrDefault(request.getMethod(), new NotAllowedHandler(allowedMethods));
        return handler.handleRequest(request);
    }

    public void registerTestRoutes() {
        populateRouteMap(new Route("/simple_get", new HashMap<String, IHandler>() {{
            put("GET", new RouteHandler());
            put("HEAD", new HeadHandler());
        }}));
        populateRouteMap(new Route("/method_options", new HashMap<String, IHandler>() {{
            put("GET", new RouteHandler());
        }}));
        populateRouteMap(new Route("/get_with_body", new HashMap<String, IHandler>() {{
            put("HEAD", new HeadHandler());
            put("OPTIONS", new RouteHandler());
        }}));
        populateRouteMap(new Route("/index.html", new HashMap<String, IHandler>() {{
            put("GET", new FileHandler());
            put("HEAD", new HeadHandler());
        }}));
    }

    public void populateRouteMap(Route route) {
        routes.put(route.getPath(), route.getAllowedMethods());

    }
}
