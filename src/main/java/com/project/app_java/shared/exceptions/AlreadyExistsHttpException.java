package com.project.app_java.shared.exceptions;

public class AlreadyExistsHttpException extends Exception {
    public AlreadyExistsHttpException(String entityName) {
        super(entityName + " already exists");
    }
}
