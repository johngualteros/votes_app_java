package com.project.app_java.shared.utils;

import static java.util.UUID.randomUUID;

public class Generators {
    /**
     * Generates a random UUID.
     *
     * @return String
     */
    public static String generateUUID() {
        return randomUUID().toString();
    }
}
