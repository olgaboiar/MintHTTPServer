package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.util.Map;

import static com.olgaboiar.mint.Constants.STATUS_CODE_405;

public class NotAllowedHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, Map<String, Map<String, IHandler>> routes) throws IOException {
        Response response =  new ResponseGenerator().generateResponse(STATUS_CODE_405, "");
        Map<String, IHandler> methodHandlers = routes.get(request.getUri());
        String[] allowedMethods = new String[methodHandlers.keySet().size()];
        allowedMethods = methodHandlers.keySet().toArray(allowedMethods);
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
