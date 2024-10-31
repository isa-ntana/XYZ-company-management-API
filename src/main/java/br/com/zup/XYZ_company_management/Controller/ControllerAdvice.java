package br.com.zup.XYZ_company_management.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

    public List<Map<String, String>> handle(MethodArgumentNotValidException exception) {
        List<Map<String, String>> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> {
                    Map<String, String> mapFieldErrors = new HashMap<>();
                    mapFieldErrors.put("field", fieldError.getField());
                    mapFieldErrors.put("message", fieldError.getDefaultMessage());
                    return mapFieldErrors;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY).getBody();
    }
}