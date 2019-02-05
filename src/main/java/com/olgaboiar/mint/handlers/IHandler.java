package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;

import java.io.IOException;
import java.util.Map;

public interface IHandler {
    Response handleRequest(Request request, Map<String, Map<String, IHandler>> routes) throws IOException;
}
