package com.olgaboiar.mint;

import java.io.IOException;

import static com.olgaboiar.mint.Constants.STATUS_CODE_404;

public class NotFoundHandler implements IHandler {
    @Override
    public Response handleRequest(Request request) throws IOException {
        return new ResponseGenerator().generateResponse(STATUS_CODE_404, "");
    }
}
