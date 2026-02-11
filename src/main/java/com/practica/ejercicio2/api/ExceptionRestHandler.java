package com.practica.ejercicio2.api;

import com.practica.ejercicio2.common.CodeError;
import com.practica.ejercicio2.services.exceptions.FacturaNotFoundException;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class ExceptionRestHandler {
    private final MessageSource messageSource;

    public ExceptionRestHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(FacturaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFacturaNotFoundException(
            FacturaNotFoundException ex,
            HttpServletRequest request,
            Locale locale
    ) {
        log.info("[handleFacturaNotFoundException]");
        String msg = messageSource.getMessage(CodeError.FACTURA_NOT_FOUND.getMessage(), null, locale);
        ErrorResponse error = ErrorResponse.builder()
                        .status(CodeError.FACTURA_NOT_FOUND.getCode())
                        .error("Business Error")
                        .message(msg)
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build();
        log.debug("[error {}]",error);
        return ResponseEntity
                .status(CodeError.FACTURA_NOT_FOUND.getCode())
                .body(error);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(
            ServiceException ex,
            HttpServletRequest request,
            Locale locale
    ) {
        log.info("[handleServiceException]");
        String msg = messageSource.getMessage(CodeError.SERVICE.getMessage(), null, locale);
        ErrorResponse error = ErrorResponse.builder()
                .status(CodeError.SERVICE.getCode())
                .error("Business Error")
                .message(msg)
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        log.debug("[error {}]",error);
        return ResponseEntity
                .status(CodeError.SERVICE.getCode())
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        log.info("[handleGenericException]");
         ErrorResponse error = ErrorResponse.builder()
                 .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                 .error("Internal Server Error")
                 .message("Unexpected error occurred")
                 .path(request.getRequestURI())
                 .timestamp(LocalDateTime.now())
                 .build();
        log.debug("[error {}]",error);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}

