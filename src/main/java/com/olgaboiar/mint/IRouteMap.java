package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.IHandler;

import java.util.ArrayList;
import java.util.Map;

public interface IRouteMap {
    Map<String, Map<String, IHandler>> getMap();
    void registerTestRoutes();
    ArrayList<String> getAllowedMethods(String path);
}
