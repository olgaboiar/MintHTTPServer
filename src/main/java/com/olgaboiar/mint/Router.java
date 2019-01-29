package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Router {
    ResponseGenerator responseGenerator = new ResponseGenerator();
    Response response;
    private String[] allowedRoutes = {"/simple_get", "/method_options", "/get_with_body"};

    public Response route(Request request) throws IOException {
        String file = "." + request.getRequestedFile();
        File searchForFile = new File(file);
        if (searchForFile.isFile()) {
            return new FileHandler().handleRequest(request);
        } else if (Arrays.asList(allowedRoutes).contains(request.getRequestedFile())) {
            System.out.println("route allowed");
            response = responseGenerator.generateResponse(STATUS_CODE_200, "");
        }
        else {
            return new NotFoundHandler().handleRequest(request);
        }
        return response;
    }
}
