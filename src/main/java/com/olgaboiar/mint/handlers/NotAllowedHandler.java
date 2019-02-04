package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.util.Set;

import static com.olgaboiar.mint.Constants.STATUS_CODE_405;

public class NotAllowedHandler implements IHandler {
    Set<String> allowedMethods;
    Response response;

    public NotAllowedHandler(Set<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    @Override
    public Response handleRequest(Request request) throws IOException {
        String body = "";
        response =  new ResponseGenerator().generateResponse(STATUS_CODE_405, body);
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
