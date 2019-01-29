package com.olgaboiar.mint;

import java.io.IOException;

interface IHandler {
    Response handleRequest(Request request) throws IOException;
}
