package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.io.IOException;
import java.util.ArrayList;

public class OptionsHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, IRouteMap routes) throws IOException {
        Response response =  ResponseGenerator.generateResponse(Constants.Status.STATUS_CODE_200);
        ArrayList<String> allowedMethods = routes.getAllowedMethods(request.getUri());
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
