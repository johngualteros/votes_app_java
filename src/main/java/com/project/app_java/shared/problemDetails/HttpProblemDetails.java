package com.project.app_java.shared.problemDetails;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.shared.exceptions.BadRequestHttpException;
import com.project.app_java.shared.exceptions.InternalServerHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class HttpProblemDetails extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AlreadyExistsHttpException.class)
    ProblemDetail handleAlreadyExistsHttpException(AlreadyExistsHttpException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Already Exists");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("cause", e.getCause());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(BadRequestHttpException.class)
    ProblemDetail handleBadRequestHttpException(BadRequestHttpException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Bad Request");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("cause", e.getCause());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(InternalServerHttpException.class)
    ProblemDetail handleInternalServerHttpException(InternalServerHttpException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("cause", e.getCause());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
