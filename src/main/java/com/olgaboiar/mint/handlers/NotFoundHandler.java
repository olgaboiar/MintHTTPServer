package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Constants;
import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.util.Map;

public class NotFoundHandler implements IHandler {
    @Override
    public Response handleRequest(Request request, Map<String, Map<String, IHandler>> routes) throws IOException {
        return new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_404.toString(), "");
    }
}
