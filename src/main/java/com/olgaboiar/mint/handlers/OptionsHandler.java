package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Constants;
import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.util.Map;

public class OptionsHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, Map<String, Map<String, IHandler>> routes) throws IOException {
        Response response =  new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_200.toString(), "");
        Map<String, IHandler> methodHandlers = routes.get(request.getUri());
        String[] allowedMethods = new String[methodHandlers.keySet().size()];
        allowedMethods = methodHandlers.keySet().toArray(allowedMethods);
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
