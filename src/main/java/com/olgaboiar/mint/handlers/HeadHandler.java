package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;

import static com.olgaboiar.mint.Constants.STATUS_CODE_200;

public class HeadHandler implements IHandler {
    @Override
    public Response handleRequest(Request request) throws IOException {
        String body = "";
        return new ResponseGenerator().generateResponse(STATUS_CODE_200, body);
    }
}
