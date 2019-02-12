package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.util.*;

public class Router {
    IRouteMap routes;

    public Router(IRouteMap routes) {
        this.routes = routes;
    }

    public Response route(Request request) throws IOException {
        Map<String, Map<String, IHandler>> routesMap = routes.getMap();
        Map<String, IHandler> methodHandlers = routesMap.get(request.getUri());
        if (!methodHandlersExist(methodHandlers)) {
            return new NotFoundHandler().handleRequest(request, routes);
        }
        IHandler handler =  methodHandlers.getOrDefault(request.getMethod(), new NotAllowedHandler());
        return handler.handleRequest(request, routes);
    }

    private boolean methodHandlersExist (Map<String, IHandler> methodHandlers) {
        return methodHandlers != null;
    }

}
