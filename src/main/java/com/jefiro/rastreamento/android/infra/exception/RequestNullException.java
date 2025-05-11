package com.jefiro.rastreamento.android.infra.exception;

public class RequestNullException extends RuntimeException {
    public RequestNullException() {
        super("This request is null");
    }
}

