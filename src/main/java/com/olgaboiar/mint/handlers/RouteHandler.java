package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.io.IOException;

public class RouteHandler implements IHandler {
    @Override
    public Response handleRequest(Request request, RouteMap routes) throws IOException {
        return new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_200.toString(), "");
    }
}
