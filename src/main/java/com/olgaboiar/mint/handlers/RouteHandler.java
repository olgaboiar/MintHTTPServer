package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

public class RouteHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, IRouteMap routes) {
        return ResponseGenerator.generateResponse(Constants.Status.STATUS_CODE_200);
    }
}
