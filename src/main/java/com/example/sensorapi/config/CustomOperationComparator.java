package com.example.sensorapi.config;

import io.swagger.v3.oas.models.PathItem;

import java.util.Comparator;
import java.util.Map;

public class CustomOperationComparator implements Comparator<String> {

    @Override
    public int compare(String path1, String path2) {

        if (path1.equals("/sensors") && path2.startsWith("/sensors/")) {
            return -1;
        }
        if (path2.equals("/sensors") && path1.startsWith("/sensors/")) {
            return 1;
        }
        if (path1.startsWith("/sensors/luminosity") && path2.startsWith("/sensors/temperature")) {
            return -1;
        }
        if (path2.startsWith("/sensors/luminosity") && path1.startsWith("/sensors/temperature")) {
            return 1;
        }

        return path1.compareTo(path2);
    }
}
