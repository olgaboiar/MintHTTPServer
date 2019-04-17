package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

public class PostHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, IRouteMap routes) {
        String body = request.getBody();
        return ResponseGenerator.generateResponse(Constants.Status.STATUS_CODE_200, body);
    }
}
