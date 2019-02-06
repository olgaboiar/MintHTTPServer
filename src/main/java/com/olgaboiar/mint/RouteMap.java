package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.util.HashMap;
import java.util.Map;

public class RouteMap implements IRouteMap {
    private Map<String, Map<String, IHandler>> routes = new HashMap<>();

    public RouteMap() {
        registerTestRoutes();
    }

    @Override
    public Map<String, Map<String, IHandler>> getMap() {
        return routes;
    }

    @Override
    public void setMethodHandlers(Route route) {
        routes.put(route.getPath(), route.getAllowedMethods());
    }

    @Override
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
            put("GET", new RedirectHandler("http://0.0.0.0:5000/simple_get"));
        }}));
    }

}