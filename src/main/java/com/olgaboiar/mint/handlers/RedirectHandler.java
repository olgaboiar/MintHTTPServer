package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.util.Map;

import static com.olgaboiar.mint.Constants.STATUS_CODE_301;

public class RedirectHandler implements IHandler {
    String redirectTarget;

    public RedirectHandler(String redirectTarget) {
        this.redirectTarget = redirectTarget;
    }

    @Override
    public Response handleRequest(Request request, Map<String, Map<String, IHandler>> routes) throws IOException {
        Response response =  new ResponseGenerator().generateResponse(STATUS_CODE_301, "");
        response.getHeader().setRedirection(redirectTarget);
        return response;
    }
}
