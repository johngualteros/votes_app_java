package com.project.app_java.shared.exceptions;

public class NotFoundHttpException extends Exception{
    public NotFoundHttpException(String entityName) {
        super(entityName + " not found");
    }
}
