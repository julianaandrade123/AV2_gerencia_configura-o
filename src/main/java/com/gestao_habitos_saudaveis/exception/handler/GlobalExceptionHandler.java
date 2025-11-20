package com.gestao_habitos_saudaveis.exception.handler;

import com.gestao_habitos_saudaveis.exception.DadosInvalidosException;
import com.gestao_habitos_saudaveis.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage());
    }

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<Map<String, Object>> handleDadosInvalidos(DadosInvalidosException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Dados inválidos", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor", ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
