package com.project.app_java.shared.utils;

public class Convertors {
    /**
     * method to convert a string to camel case
     * @param str
     * @return String
     * */
    public static String convertToCamelCase(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.substring(0, 1).toUpperCase());
            sb.append(word.substring(1).toLowerCase());
        }
        return sb.toString();
    }
}