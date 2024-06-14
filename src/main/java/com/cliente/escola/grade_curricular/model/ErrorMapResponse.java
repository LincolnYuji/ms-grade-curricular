package com.cliente.escola.grade_curricular.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ErrorMapResponse {
    private int httpStatus;
    private Map<String, String> erros;
    private Long timeStamp;
}
