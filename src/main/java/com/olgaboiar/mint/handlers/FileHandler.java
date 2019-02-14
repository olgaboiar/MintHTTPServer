package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler implements IHandler {
    @Override
    public Response handleRequest(Request request, IRouteMap routes) throws IOException {
        String file = "." + request.getUri();
        List<String> fileContent = Files.readAllLines(Paths.get(file));
        String body = String.join("", fileContent);
        return new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_200, body);
    }
}
