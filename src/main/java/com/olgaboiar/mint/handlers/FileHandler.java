package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Constants;
import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileHandler implements IHandler {
    @Override
    public Response handleRequest(Request request, Map<String, Map<String, IHandler>> routes) throws IOException {
        String file = "." + request.getUri();
        List<String> fileContent = Files.readAllLines(Paths.get(file));
        String body = String.join("", fileContent);
        return new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_200.toString(), body);
    }
}
