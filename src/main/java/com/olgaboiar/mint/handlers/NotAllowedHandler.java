package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.io.IOException;
import java.util.ArrayList;

public class NotAllowedHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, RouteMap routes) throws IOException {
        Response response =  new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_405.toString(), "");
        ArrayList<String> allowedMethods = routes.getAllowedMethods(request.getUri());
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
