package com.cliente.escola.grade_curricular.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private String mensagem;
    private int httpStatus;
    private Long timeStamp;
}
