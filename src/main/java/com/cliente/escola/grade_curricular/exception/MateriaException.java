package com.cliente.escola.grade_curricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class MateriaException extends RuntimeException{

    private static final long serialVersionUID = -6146843349777180810L;

    private final HttpStatus httpStatus;

    public MateriaException(final String mensagem, final HttpStatus httpStatus) {
        super(mensagem);
        this.httpStatus = httpStatus;
    }
}
