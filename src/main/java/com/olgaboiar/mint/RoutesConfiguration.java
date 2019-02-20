package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
        NOT_ALLOWED_REQUEST_HANDLER {
            public NotAllowedHandler getHandler() {
                return new NotAllowedHandler();
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
        },
        OPTIONS_REQUEST_HANDLER {
            public OptionsHandler getHandler() {
                return new OptionsHandler();
            }
        };
        public abstract IHandler getHandler();
    }

    @Override
    public void createRoutes(String filePath) throws IOException {
        allRoutes = new ArrayList<> ();
        Yaml yaml = new Yaml();
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        String document = new String(encoded, StandardCharsets.UTF_8);
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
                        handler = Handler.NOT_ALLOWED_REQUEST_HANDLER.getHandler();
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
