package com.jefiro.rastreamento.android.infra.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorMensage {
    private HttpStatus status;
    private String mensage;
}
