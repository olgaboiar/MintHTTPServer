package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;

import java.io.IOException;

public interface IHandler {
    Response handleRequest(Request request) throws IOException;
}
