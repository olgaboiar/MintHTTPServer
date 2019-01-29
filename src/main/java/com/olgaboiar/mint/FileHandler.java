package com.olgaboiar.mint;

import java.io.File;
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
        if (request.getMethod().equals("HEAD")) {
            body = "";
        }
        return new ResponseGenerator().generateResponse(STATUS_CODE_200, body);
    }
}
