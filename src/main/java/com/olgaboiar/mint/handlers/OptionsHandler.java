package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.util.ArrayList;

public class OptionsHandler implements IHandler {
    ArrayList<String> allowedMethods;

    public OptionsHandler(ArrayList<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    @Override
    public Response handleRequest(Request request, IRouteMap routes) {
        Response response =  ResponseGenerator.generateResponse(Constants.Status.STATUS_CODE_200);
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
