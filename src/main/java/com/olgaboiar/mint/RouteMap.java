package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RouteMap implements IRouteMap {
    IRoutesConfiguration serverRoutes;
    private Map<String, Map<String, IHandler>> routes = new HashMap<>();

    public RouteMap(IRoutesConfiguration serverRoutes) {
        this.serverRoutes = serverRoutes;
        registerTestRoutes();
    }

    @Override
    public Map<String, Map<String, IHandler>> getMap() {
        return routes;
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
        ArrayList<Route> allRoutes = serverRoutes.getAllRoutes();
        for (Route r : allRoutes) routes.put(r.getPath(), r.getAllowedMethods());
    }
}
