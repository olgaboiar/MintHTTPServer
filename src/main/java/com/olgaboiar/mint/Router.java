package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(methodList);
        IHandler handler =  methodList.get(request.getMethod());
        if (handler == null) {
            return new NotAllowedHandler().handleRequest(request);
        }
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
        setHandler(new Route("/simple_get", "HEAD"), new HEADHandler());
        setHandler(new Route("/method_options", "GET"), new RouteHandler());
        setHandler(new Route("/get_with_body", "GET"), new NotAllowedHandler());
        setHandler(new Route("/get_with_body", "HEAD"), new HEADHandler());
        setHandler(new Route("/index.html", "GET"), new FileHandler());
        setHandler(new Route("/index.html", "HEAD"), new HEADHandler());
    }
}
