package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.*;

public class Router {
    private Map<String, Map<String, IHandler>> routes = new HashMap<>();
//    String[] allowedForThisPath;

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
            System.out.println("1");
            methodList = new HashMap();
            routes.put(route.getPath(), methodList);
        }
        System.out.println(methodList);
        String[] allowedForThisPath = route.getAllowedMethods();
        System.out.println(allowedForThisPath.length);
        for (String method : allowedForThisPath) {
            System.out.println(method);
            methodList.put(method, setHandler(method, allowedForThisPath));
        }
        System.out.println(methodList);
//        methodList.put(route.getMethod(), routeHandler);
    }

    public IHandler setHandler(String method, String[] allowedForThisPath) {
        if(method == "GET") {
            return new RouteHandler();
        }
        if(method == "HEAD") {
            return new HeadHandler();
        }
        if(method == "OPTIONS") {
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
//        setHandler(new Route("/method_options", "GET"), new RouteHandler());
//        setHandler(new Route("/get_with_body", "HEAD"), new HeadHandler());
//        setHandler(new Route("/get_with_body", "OPTIONS"), new HeadHandler());
//        setHandler(new Route("/index.html", "GET"), new FileHandler());
//        setHandler(new Route("/index.html", "HEAD"), new HeadHandler());
    }

//    public void registerTestRoutes() {
//        populateRouteMap(new Route("/simple_get", new HashMap<String, IHandler>() {{
//            put("GET", new RouteHandler());
//            put("HEAD", new HeadHandler());
//        }}));
//        populateRouteMap(new Route("/method_options", new HashMap<String, IHandler>() {{
//            put("GET", new RouteHandler());
//            put("HEAD", new HeadHandler());
//            put("OPTIONS", new OptionsHandler());
//        }}));
//        populateRouteMap(new Route("/get_with_body", new HashMap<String, IHandler>() {{
//            put("HEAD", new HeadHandler());
//            put("OPTIONS", new RouteHandler());
//        }}));
//        populateRouteMap(new Route("/index.html", new HashMap<String, IHandler>() {{
//            put("GET", new FileHandler());
//            put("HEAD", new HeadHandler());
//        }}));
//    }
//
//    public void populateRouteMap(Route route) {
//        routes.put(route.getPath(), route.getAllowedMethods());
//
//    }

}
