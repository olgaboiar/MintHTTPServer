package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MockRouteMap implements IRouteMap {
    private Map<String, Map<String, IHandler>> routes = new HashMap<>();
    MockRoutesConfiguration serverTestRoutes;

    public MockRouteMap(MockRoutesConfiguration serverTestRoutes) {
        this.serverTestRoutes = serverTestRoutes;
        registerTestRoutes();
    }

    @Override
    public Map<String, Map<String, IHandler>> getMap() {
        return routes;
    }

    @Override
    public void registerTestRoutes() {
        ArrayList<Route> allRoutes = serverTestRoutes.getAllRoutes();
        for (Route r : allRoutes) routes.put(r.getPath(), r.getAllowedMethods());

    }

    @Override
    public ArrayList<String> getAllowedMethods(String path) {
        Map<String, IHandler> methodHandlers = routes.get(path);
        ArrayList<String> allowedMethods = new ArrayList<String>(methodHandlers.keySet().size());
        allowedMethods.addAll(methodHandlers.keySet());
        return allowedMethods;
    }
}
