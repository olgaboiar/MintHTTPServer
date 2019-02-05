package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.*;

public class Router {
    private Map<String, Map<String, IHandler>> routes = new HashMap<>();
//    Map<String, IHandler> methodHandlers;

    public Router() {
        registerTestRoutes();
    }

    public Response route(Request request) throws IOException {
        Map<String, IHandler> methodHandlers = routes.get(request.getUri());
        if (methodHandlers == null) {
            return new NotFoundHandler().handleRequest(request);
        }
        String[] allowedMethods = new String[methodHandlers.keySet().size()];
        allowedMethods = methodHandlers.keySet().toArray(allowedMethods);
        IHandler handler =  methodHandlers.getOrDefault(request.getMethod(), new NotAllowedHandler(allowedMethods));
        return handler.handleRequest(request);
    }

    public void setMethodHandlers(Route route) {
        Map<String, IHandler> methodHandlers = routes.get(route.getPath());
        if(methodHandlers == null) {
            methodHandlers = new HashMap();
            routes.put(route.getPath(), methodHandlers);
        }
        String[] allowedForThisPath = route.getAllowedMethods();
        for (String method : allowedForThisPath) {
            methodHandlers.put(method, setHandler(method, allowedForThisPath));
        }
    }

    public IHandler setHandler(String method, String[] allowedForThisPath) {
        switch (method) {
            case "GET":
                return new RouteHandler();
            case "HEAD":
                return new HeadHandler();
            case "OPTIONS":
                return new OptionsHandler(allowedForThisPath);
        }
        return new NotAllowedHandler(allowedForThisPath);
    }

    public void registerTestRoutes() {
        setMethodHandlers(new Route("/simple_get", new String[] {"GET", "HEAD"}));
        setMethodHandlers(new Route("/method_options", new String[] {"GET", "HEAD", "OPTIONS"}));
        setMethodHandlers(new Route("/method_options2", new String[] {"GET", "HEAD", "OPTIONS", "PUT", "POST"}));
        setMethodHandlers(new Route("/get_with_body", new String[] {"HEAD", "OPTIONS"}));
        setMethodHandlers(new Route("/index.html", new String[] {"GET", "HEAD"}));
    }

}
