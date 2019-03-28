package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;
import com.olgaboiar.mint.utils.FileReader;

import java.io.IOException;

public class FileHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, IRouteMap routes) throws IOException {
        String body = new FileReader().readFileToString(request.getUri());
        return ResponseGenerator.generateResponse(request, Constants.Status.STATUS_CODE_200, body);
    }
}
