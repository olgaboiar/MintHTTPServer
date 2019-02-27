package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.IRouteMap;
import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;

import java.util.ArrayList;

public class NoMethodHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, IRouteMap routes) {
        ArrayList<String> allowedMethods = routes.getAllowedMethods(request.getUri());
        allowedMethods.add("OPTIONS");
        if (request.getMethod().equals("OPTIONS")) {
            return new OptionsHandler(allowedMethods).handleRequest(request, routes);
        } else {
            return new NotAllowedHandler(allowedMethods).handleRequest(request, routes);
        }
    }
}
