package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MockRoutesConfiguration implements IRoutesConfiguration {
    ArrayList<Route> allTestRoutes;

    public MockRoutesConfiguration() {
        createRoutes();
    }

    @Override
    public void createRoutes() {
        allTestRoutes = new ArrayList<> ();
        allTestRoutes.add(new Route("/test", new HashMap<String, IHandler>() {{
            put(RoutesConfiguration.Method.GET.toString(), new RouteHandler());
            put(RoutesConfiguration.Method.HEAD.toString(), new HeadHandler());
            put(RoutesConfiguration.Method.OPTIONS.toString(), new OptionsHandler());
            put(RoutesConfiguration.Method.PUT.toString(), new NotAllowedHandler());
            put(RoutesConfiguration.Method.POST.toString(), new NotAllowedHandler());
        }}));
        allTestRoutes.add(new Route("/index.html", new HashMap<String, IHandler>() {{
            put(RoutesConfiguration.Method.GET.toString(), new FileHandler());
            put(RoutesConfiguration.Method.HEAD.toString(), new HeadHandler());
        }}));
        allTestRoutes.add(new Route("/test_redirect", new HashMap<String, IHandler>() {{
            put(RoutesConfiguration.Method.GET.toString(), new RedirectHandler("http://0.0.0.0:5000/simple_get"));
        }}));
        allTestRoutes.add(new Route("/echo_body", new HashMap<String, IHandler>() {{
            put(RoutesConfiguration.Method.POST.toString(), new PostHandler());
        }}));
    }

    @Override
    public ArrayList<Route> getAllRoutes() {
        return allTestRoutes;
    }
}
