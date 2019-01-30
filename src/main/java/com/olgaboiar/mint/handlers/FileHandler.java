package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.ResponseGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.olgaboiar.mint.Constants.STATUS_CODE_200;

public class FileHandler implements IHandler {
    @Override
    public Response handleRequest(Request request) throws IOException {
        String file = "." + request.getRequestedFile();
        List<String> fileContent = Files.readAllLines(Paths.get(file));
        String body = String.join("", fileContent);
        return new ResponseGenerator().generateResponse(STATUS_CODE_200, body);
    }
}
