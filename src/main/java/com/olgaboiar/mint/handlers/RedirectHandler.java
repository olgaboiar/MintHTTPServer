package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.io.IOException;

public class RedirectHandler implements IHandler {
    String redirectTarget;

    public RedirectHandler(String redirectTarget) {
        this.redirectTarget = redirectTarget;
    }

    @Override
    public Response handleRequest(Request request, IRouteMap routes) throws IOException {
        Response response =  new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_301.toString(), "");
        response.getHeader().setRedirection(redirectTarget);
        return response;
    }
}
