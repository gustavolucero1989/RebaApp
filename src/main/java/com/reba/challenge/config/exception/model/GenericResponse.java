package com.reba.challenge.config.exception.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse<T> {
    private int statusCode;
    private String status;
    private T data;
}
