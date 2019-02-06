package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Constants;
import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.util.Map;

public class RedirectHandler implements IHandler {
    String redirectTarget;

    public RedirectHandler(String redirectTarget) {
        this.redirectTarget = redirectTarget;
    }

    @Override
    public Response handleRequest(Request request, Map<String, Map<String, IHandler>> routes) throws IOException {
        Response response =  new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_301.toString(), "");
        response.getHeader().setRedirection(redirectTarget);
        return response;
    }
}