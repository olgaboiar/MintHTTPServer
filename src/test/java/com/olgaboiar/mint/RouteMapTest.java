package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
