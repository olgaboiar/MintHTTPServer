package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public void setHandler(Route route, IHandler routeHandler) {
        Map<String, IHandler> methodList = routes.get(route.getPath());
        if(methodList == null) {
            methodList = new HashMap();
            routes.put(route.getPath(), methodList);
        }
        methodList.put(route.getMethod(), routeHandler);
    }

    public void registerTestRoutes() {
        setHandler(new Route("/simple_get", "GET"), new RouteHandler());
        setHandler(new Route("/simple_get", "HEAD"), new HeadHandler());
        setHandler(new Route("/method_options", "GET"), new RouteHandler());
        setHandler(new Route("/get_with_body", "HEAD"), new HeadHandler());
        setHandler(new Route("/get_with_body", "OPTIONS"), new HeadHandler());
        setHandler(new Route("/index.html", "GET"), new FileHandler());
        setHandler(new Route("/index.html", "HEAD"), new HeadHandler());
    }
}
