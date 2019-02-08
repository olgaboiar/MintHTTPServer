package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.HeadHandler;
import com.olgaboiar.mint.handlers.IHandler;
import com.olgaboiar.mint.handlers.RouteHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RouteMapTest {
    RouteMap testMap;
    MockRoutesConfiguration serverTestRoutes = new MockRoutesConfiguration();

    @BeforeEach
    public void init(){
        testMap = new RouteMap(serverTestRoutes);
    }

    @Test
    void returnsAllAllowedMethodsForTestPath() {
        ArrayList<String> actual = testMap.getAllowedMethods("/index.html");
        ArrayList<String> expected = new ArrayList<String> ();
        expected.add("HEAD");
        expected.add("GET");
        assertEquals(expected, actual);
    }
}
