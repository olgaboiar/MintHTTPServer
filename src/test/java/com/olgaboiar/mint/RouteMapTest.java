package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.HeadHandler;
import com.olgaboiar.mint.handlers.IHandler;
import com.olgaboiar.mint.handlers.RouteHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RouteMapTest {
    RouteMap testMap;

    @BeforeEach
    public void init(){
        testMap = new RouteMap();
    }

    @Test
    void getMapReturnsRouteMap() {
        Map<String, Map<String, IHandler>> actual = testMap.getMap();
        Map<String, Map<String, IHandler>> expected = new HashMap<>();
        Map<String, IHandler> methodsSimpleGet = new HashMap<>();
        methodsSimpleGet.put("GET", new RouteHandler());
        methodsSimpleGet.put("HEAD", new HeadHandler());
        expected.put("/simple_get", methodsSimpleGet);
//        expected.put("/method_options", route.getAllowedMethods());
//        expected.put("/method_options2", route.getAllowedMethods());
//        expected.put("/get_with_body", route.getAllowedMethods());
//        expected.put("/index.html", route.getAllowedMethods());
//        expected.put("/redirect", route.getAllowedMethods());
        assertEquals(actual, expected);
    }

}

//
//    @Override
//    public void setMethodHandlers(Route route) {
//        routes.put(route.getPath(), route.getAllowedMethods());
//    }
//
//    @Override
//    public ArrayList<String> getAllowedMethods(String path) {
//        Map<String, IHandler> methodHandlers = routes.get(path);
//        ArrayList<String> allowedMethods = new ArrayList<String>(methodHandlers.keySet().size());
//        allowedMethods.addAll(methodHandlers.keySet());
//        return allowedMethods;
//    }
//
//    @Override
//    public void registerTestRoutes() {
//        setMethodHandlers(new Route("/simple_get", new HashMap<String, IHandler>() {{
//            put(Method.GET.toString(), new RouteHandler());
//            put(Method.HEAD.toString(), new HeadHandler());
//        }}));
//        setMethodHandlers(new Route("/method_options", new HashMap<String, IHandler>() {{
//            put(Method.GET.toString(), new RouteHandler());
//            put(Method.HEAD.toString(), new HeadHandler());
//            put(Method.OPTIONS.toString(), new OptionsHandler());
//        }}));
//        setMethodHandlers(new Route("/method_options2", new HashMap<String, IHandler>() {{
//            put(Method.GET.toString(), new RouteHandler());
//            put(Method.HEAD.toString(), new HeadHandler());
//            put(Method.OPTIONS.toString(), new OptionsHandler());
//            put(Method.PUT.toString(), new NotAllowedHandler());
//            put(Method.POST.toString(), new NotAllowedHandler());
//        }}));
//        setMethodHandlers(new Route("/get_with_body", new HashMap<String, IHandler>() {{
//            put(Method.HEAD.toString(), new HeadHandler());
//            put(Method.OPTIONS.toString(), new OptionsHandler());
//        }}));
//        setMethodHandlers(new Route("/index.html", new HashMap<String, IHandler>() {{
//            put(Method.GET.toString(), new FileHandler());
//            put(Method.HEAD.toString(), new HeadHandler());
//        }}));
//        setMethodHandlers(new Route("/redirect", new HashMap<String, IHandler>() {{
//            put(Method.GET.toString(), new RedirectHandler("http://0.0.0.0:5000/simple_get"));
//        }}));
//    }