package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.*;

public class Router {
    RouteMap routes;

    public Router(RouteMap routes) {
        this.routes = routes;
    }

    public Response route(Request request) throws IOException {
        Map<String, Map<String, IHandler>> routesMap = routes.getMap();
        Map<String, IHandler> methodHandlers = routesMap.get(request.getUri());
        if (methodHandlers == null) {
            return new NotFoundHandler().handleRequest(request, routesMap);
        }
        IHandler handler =  methodHandlers.getOrDefault(request.getMethod(), new NotAllowedHandler());
        return handler.handleRequest(request, routesMap);
    }

}
