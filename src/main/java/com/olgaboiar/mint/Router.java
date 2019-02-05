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
        Map<String, IHandler> methodHandlers = routes.get(request.getUri());
        if (methodHandlers == null) {
            return new NotFoundHandler().handleRequest(request, routes);
        }
        IHandler handler =  methodHandlers.getOrDefault(request.getMethod(), new NotAllowedHandler());
        return handler.handleRequest(request, routes);
    }

    public void setMethodHandlers(Route route) {
            routes.put(route.getPath(), route.getAllowedMethods());
    }


    public void registerTestRoutes() {
        setMethodHandlers(new Route("/simple_get", new HashMap<String, IHandler>() {{
            put("GET", new RouteHandler());
            put("HEAD", new HeadHandler());
        }}));
        setMethodHandlers(new Route("/method_options", new HashMap<String, IHandler>() {{
            put("GET", new RouteHandler());
            put("HEAD", new HeadHandler());
            put("OPTIONS", new OptionsHandler());
        }}));
        setMethodHandlers(new Route("/method_options2", new HashMap<String, IHandler>() {{
            put("GET", new RouteHandler());
            put("HEAD", new HeadHandler());
            put("OPTIONS", new OptionsHandler());
            put("PUT", new NotAllowedHandler());
            put("POST", new NotAllowedHandler());
        }}));
        setMethodHandlers(new Route("/get_with_body", new HashMap<String, IHandler>() {{
            put("HEAD", new HeadHandler());
            put("OPTIONS", new OptionsHandler());
        }}));
        setMethodHandlers(new Route("/index.html", new HashMap<String, IHandler>() {{
            put("GET", new FileHandler());
            put("HEAD", new HeadHandler());
        }}));
        setMethodHandlers(new Route("/redirect", new HashMap<String, IHandler>() {{
            put("GET", new RedirectHandler());
        }}));
    }
}
