package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RouteMap implements IRouteMap {
    private Map<String, Map<String, IHandler>> routes = new HashMap<>();

    public RouteMap() {
        registerTestRoutes();
    }

    enum Method {
        GET,
        HEAD,
        POST,
        PUT,
        OPTIONS
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
    public ArrayList<String> getAllowedMethods(String path) {
        Map<String, IHandler> methodHandlers = routes.get(path);
        ArrayList<String> allowedMethods = new ArrayList<String>(methodHandlers.keySet().size());
        allowedMethods.addAll(methodHandlers.keySet());
        return allowedMethods;
    }

    @Override
    public void registerTestRoutes() {
        setMethodHandlers(new Route("/simple_get", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RouteHandler());
            put(Method.HEAD.toString(), new HeadHandler());
        }}));
        setMethodHandlers(new Route("/method_options", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RouteHandler());
            put(Method.HEAD.toString(), new HeadHandler());
            put(Method.OPTIONS.toString(), new OptionsHandler());
        }}));
        setMethodHandlers(new Route("/method_options2", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RouteHandler());
            put(Method.HEAD.toString(), new HeadHandler());
            put(Method.OPTIONS.toString(), new OptionsHandler());
            put(Method.PUT.toString(), new NotAllowedHandler());
            put(Method.POST.toString(), new NotAllowedHandler());
        }}));
        setMethodHandlers(new Route("/get_with_body", new HashMap<String, IHandler>() {{
            put(Method.HEAD.toString(), new HeadHandler());
            put(Method.OPTIONS.toString(), new OptionsHandler());
        }}));
        setMethodHandlers(new Route("/index.html", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new FileHandler());
            put(Method.HEAD.toString(), new HeadHandler());
        }}));
        setMethodHandlers(new Route("/redirect", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RedirectHandler("http://0.0.0.0:5000/simple_get"));
        }}));
    }

}
