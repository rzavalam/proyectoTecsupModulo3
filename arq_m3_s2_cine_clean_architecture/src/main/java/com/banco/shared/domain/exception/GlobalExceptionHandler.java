package com.banco.shared.domain.exception;

import com.banco.accounts.domain.exception.PeliculaNoEncontradaException;
import com.banco.accounts.domain.exception.SalaNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SalaNoEncontradaException.class)
    public ResponseEntity<?> handleSalaNoEncontrada(
            SalaNoEncontradaException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        Map.of(

                                "status", 404,
                                "error", "Sala no encontrada",
                                "message", ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(PeliculaNoEncontradaException.class)
    public ResponseEntity<?> handlePeliculaNoEncontrada(
            PeliculaNoEncontradaException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        Map.of(
                                "status", 404,
                                "error", "Película no encontrada",
                                "message", ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(
            IllegalArgumentException ex) {

        return ResponseEntity
                .badRequest()
                .body(
                        Map.of(
                                "timestamp", LocalDateTime.now(),
                                "status", 400,
                                "error", "Bad Request",
                                "message", ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(
            Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Map.of(
                                "timestamp", LocalDateTime.now(),
                                "status", 500,
                                "error", "Internal Server Error",
                                "message", ex.getMessage()
                        )
                );
    }
}
