package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.*;
import java.util.*;

import com.olgaboiar.mint.utils.FileReader;
import org.yaml.snakeyaml.Yaml;

public class RoutesConfiguration implements IRoutesConfiguration {
    IHandler handler;
    ArrayList<Route> allRoutes;
    static String redirect;

    public RoutesConfiguration(String filePath) throws IOException {

        createRoutes(filePath);
    }

    enum Handler {
        GET_REQUEST_HANDLER {
            public RouteHandler getHandler() {
                return new RouteHandler();
            }
        },
        HEAD_REQUEST_HANDLER {
            public HeadHandler getHandler() {
                return new HeadHandler();
            }
        },
        NO_METHOD_REQUEST_HANDLER {
            public NoMethodHandler getHandler() {
                return new NoMethodHandler();
            }
        },
        FILE_REQUEST_HANDLER {
            public FileHandler getHandler() {
                return new FileHandler();
            }
        },
        POST_REQUEST_HANDLER {
            public PostHandler getHandler() {
                return new PostHandler();
            }
        },
        REDIRECT_REQUEST_HANDLER {
            public RedirectHandler getHandler() {
                return new RedirectHandler(redirect);
            }
        };
        public abstract IHandler getHandler();
    }

    @Override
    public void createRoutes(String filePath) throws IOException {
        allRoutes = new ArrayList<> ();
        Yaml yaml = new Yaml();
        String document = new FileReader().readFileToString(filePath);
        for (Object route : yaml.loadAll(document)) {
            Map<String, Object> currentRoute = (Map<String, Object>) route;
            String path = (String) currentRoute.get("path");
            List<Map<String, Object>> methodHandlers = (List<Map<String, Object>>) currentRoute.get("methodhandlers");
            allRoutes.add(new Route(path, new HashMap<String, IHandler>() {{
                for (Map<String, Object> methodHandler : methodHandlers) {
                    String method = (String) methodHandler.get("method");
                    String handlerEnum = (String) methodHandler.get("handler");
                    redirect = (String) methodHandler.get("redirect");
                    if (handlerEnum == null) {
                        handler = Handler.NO_METHOD_REQUEST_HANDLER.getHandler();
                    } else {
                        handler = Handler.valueOf(handlerEnum).getHandler();
                    }
                    put(method, handler);
                }
            }}));
        }
    }

    @Override
    public ArrayList<Route> getAllRoutes() {
        return allRoutes;
    }
}
