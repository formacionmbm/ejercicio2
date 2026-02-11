package com.practica.ejercicio2.api;

import java.time.LocalDateTime;

public record ErrorResponse(
     int status,
     String error,
     String message,
     String path,
     LocalDateTime timestamp) {

    //Metodo estatico
    public static ErrorBuilder builder() {
        return new ErrorBuilder();
    }

    //Clase estatica
    public static class ErrorBuilder {
        private int status;
        private String error;
        private String message;
        private String path;
        LocalDateTime timestamp;

        public ErrorBuilder status(int status) { this.status = status; return this; }
        public ErrorBuilder error(String error) { this.error = error; return this; }
        public ErrorBuilder message(String message) { this.message = message; return this; }
        public ErrorBuilder path(String path) { this.path = path; return this; }
        public ErrorBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }

        public ErrorResponse build() {
            return new ErrorResponse(status, error, message, path,timestamp);
        }
    }
}
