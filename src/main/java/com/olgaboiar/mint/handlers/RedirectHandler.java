package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

public class RedirectHandler implements IHandler {
    String redirectTarget;

    public RedirectHandler(String redirectTarget) {
        this.redirectTarget = redirectTarget;
    }

    @Override
    public Response handleRequest(Request request, IRouteMap routes) {
        Response response =  ResponseGenerator.generateResponse(request, Constants.Status.STATUS_CODE_301);
        response.getHeader().setRedirection(redirectTarget);
        return response;
    }
}
