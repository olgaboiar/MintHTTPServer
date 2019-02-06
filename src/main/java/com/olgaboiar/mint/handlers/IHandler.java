package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.Request;
import com.olgaboiar.mint.Response;
import com.olgaboiar.mint.RouteMap;

import java.io.IOException;

public interface IHandler {
    Response handleRequest(Request request, RouteMap routes) throws IOException;
}
