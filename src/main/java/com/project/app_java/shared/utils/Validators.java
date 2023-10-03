package com.project.app_java.shared.utils;

public class Validators {
    /**
     * method to validate if a string is a hex color
     * @param color
     * @return boolean isHexColor
     */
    public static boolean isHexColor(String color) {
        return color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }
}