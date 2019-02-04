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
        Set<String> allowedMethodsSet = methodList.keySet();
        String[] allowedMethods = new String[allowedMethodsSet.size()];
        allowedMethods = allowedMethodsSet.toArray(allowedMethods);
        IHandler handler =  methodList.getOrDefault(request.getMethod(), new NotAllowedHandler(allowedMethods));
        return handler.handleRequest(request);
    }

    public void setHandler(Route route) {
        Map<String, IHandler> methodList = routes.get(route.getPath());
        if(methodList == null) {
            methodList = new HashMap();
            routes.put(route.getPath(), methodList);
        }
        String[] allowedForThisPath = route.getAllowedMethods();
        for (String method : allowedForThisPath) {
            methodList.put(method, setHandler(method, allowedForThisPath));
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
        setHandler(new Route("/simple_get", new String[] {"GET", "HEAD"}));
        setHandler(new Route("/method_options", new String[] {"GET", "HEAD", "OPTIONS"}));
        setHandler(new Route("/method_options2", new String[] {"GET", "HEAD", "OPTIONS", "PUT", "POST"}));
        setHandler(new Route("/get_with_body", new String[] {"HEAD", "OPTIONS"}));
        setHandler(new Route("/index.html", new String[] {"GET", "HEAD"}));
    }

}
