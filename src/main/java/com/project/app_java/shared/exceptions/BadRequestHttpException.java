package com.project.app_java.shared.exceptions;

public class BadRequestHttpException extends Exception{
    public BadRequestHttpException(String message) {
        super(message);
    }
}
