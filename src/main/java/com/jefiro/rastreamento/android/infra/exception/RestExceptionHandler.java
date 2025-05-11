package com.jefiro.rastreamento.android.infra.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Tratamento para exceção de campo nulo ou inválido
    @ExceptionHandler(RequestNullException.class)
    private ResponseEntity<RestErrorMensage> handleRequestNullException(RequestNullException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMensage(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    // Tratamento para erro de servidor interno
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    private ResponseEntity<RestErrorMensage> handleInternalServerError(HttpServerErrorException.InternalServerError exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestErrorMensage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }

    // Tratamento para erro de "não encontrado"
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    private ResponseEntity<RestErrorMensage> handleNotFound(HttpClientErrorException.NotFound exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    // Tratamento para violação de integridade (ex. chave duplicada)
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<RestErrorMensage> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new RestErrorMensage(HttpStatus.CONFLICT, "Conflict: Duplicated tracker ID or other data integrity issue."));
    }

    // Tratamento genérico de exceções não tratadas
    @ExceptionHandler(Exception.class)
    private ResponseEntity<RestErrorMensage> handleGenericException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestErrorMensage(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + exception.getMessage()));
    }
}
