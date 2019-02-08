package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.util.ArrayList;
import java.util.HashMap;

public class RoutesConfiguration {
    ArrayList<Route> allRoutes;

    public RoutesConfiguration() {
        createRoutes();
    }

    enum Method {
        GET,
        HEAD,
        POST,
        PUT,
        OPTIONS
    }

    public void createRoutes() {
        allRoutes = new ArrayList<> ();
        allRoutes.add(new Route("/simple_get", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RouteHandler());
            put(Method.HEAD.toString(), new HeadHandler());
        }}));
        allRoutes.add(new Route("/method_options", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RouteHandler());
            put(Method.HEAD.toString(), new HeadHandler());
            put(Method.OPTIONS.toString(), new OptionsHandler());
        }}));
        allRoutes.add(new Route("/method_options2", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RouteHandler());
            put(Method.HEAD.toString(), new HeadHandler());
            put(Method.OPTIONS.toString(), new OptionsHandler());
            put(Method.PUT.toString(), new NotAllowedHandler());
            put(Method.POST.toString(), new NotAllowedHandler());
        }}));
        allRoutes.add(new Route("/get_with_body", new HashMap<String, IHandler>() {{
            put(Method.HEAD.toString(), new HeadHandler());
            put(Method.OPTIONS.toString(), new OptionsHandler());
        }}));
        allRoutes.add(new Route("/index.html", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new FileHandler());
            put(Method.HEAD.toString(), new HeadHandler());
        }}));
        allRoutes.add(new Route("/redirect", new HashMap<String, IHandler>() {{
            put(Method.GET.toString(), new RedirectHandler("http://0.0.0.0:5000/simple_get"));
        }}));
    }

    public ArrayList<Route> getAllRoutes() {
        return allRoutes;
    }
}
