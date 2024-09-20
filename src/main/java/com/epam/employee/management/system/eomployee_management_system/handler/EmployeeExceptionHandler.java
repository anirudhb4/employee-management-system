package com.epam.employee.management.system.eomployee_management_system.handler;

import com.epam.employee.management.system.eomployee_management_system.exception.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request is invalid");
        Map<String,Object> errorMap = errors.stream().collect(Collectors.toMap(x->x.getField(), x->x.getDefaultMessage()));
        problemDetail.setProperties(errorMap);
        return problemDetail;
    }

    @ExceptionHandler(EmployeeException.class)
        public ProblemDetail employeeException(EmployeeException employeeException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Employee exception");
        problemDetail.setTitle(employeeException.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail noSuchElementException(NoSuchElementException ex){
        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request is invalid");
        problemDetail.setTitle(ex.getMessage());
        return problemDetail;
    }




}
