package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

public class HeadHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, IRouteMap routes) {
        return ResponseGenerator.generateResponse(request, Constants.Status.STATUS_CODE_200);
    }
}
