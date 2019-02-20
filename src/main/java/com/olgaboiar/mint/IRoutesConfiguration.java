package com.olgaboiar.mint;


import java.io.IOException;
import java.util.ArrayList;

public interface IRoutesConfiguration {
    void createRoutes(String filePath) throws IOException;
    ArrayList<Route> getAllRoutes();
}
