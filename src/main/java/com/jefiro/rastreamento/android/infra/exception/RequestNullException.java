package com.jefiro.rastreamento.android.infra.exception;

public class RequestNullException extends RuntimeException {
    public RequestNullException(String s) {
        super("This request is null");
    }
}

