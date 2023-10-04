package com.project.app_java.shared.utils;

import java.util.Map;

public class HttpResponses {
    /**
     * Method to return a success response
     * @param data
     * @return Map<String, Object>
     * */
    public static Map<String, Object> successResponse(Object data) {
        return Map.of(
                "status", "success",
                "data", data
        );
    }

    /**
     * Method to return error response
     * @param message
     * @return Map<String, String>
     * */
    public static Map<String, String> errorResponse(String message) {
        return Map.of(
                "status", "error",
                "message", message
        );
    }
}
