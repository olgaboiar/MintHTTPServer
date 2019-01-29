package com.olgaboiar.mint;

import java.io.IOException;

import static com.olgaboiar.mint.Constants.STATUS_CODE_200;

public class RouteHandler implements IHandler {
    @Override
    public Response handleRequest(Request request) throws IOException {
        return new ResponseGenerator().generateResponse(STATUS_CODE_200, "");
    }
}
