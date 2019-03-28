package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.util.ArrayList;

public class NotAllowedHandler implements IHandler {
    ArrayList<String> allowedMethods;

    public NotAllowedHandler(ArrayList<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    @Override
    public Response handleRequest(Request request, IRouteMap routes) {
        Response response =  ResponseGenerator.generateResponse(request, Constants.Status.STATUS_CODE_405);
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
