package com.olgaboiar.mint.handlers;

import com.olgaboiar.mint.*;

import java.io.IOException;
import java.util.ArrayList;

public class OptionsHandler implements IHandler {

    @Override
    public Response handleRequest(Request request, RouteMap routes) throws IOException {
        Response response =  new ResponseGenerator().generateResponse(Constants.Status.STATUS_CODE_200.toString(), "");
        ArrayList<String> allowedMethods = routes.getAllowedMethos(request.getUri());
        response.getHeader().setAllowMethods(allowedMethods);
        return response;
    }
}
