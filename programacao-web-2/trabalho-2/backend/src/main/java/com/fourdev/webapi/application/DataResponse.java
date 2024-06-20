package com.fourdev.webapi.application;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

/**
 * @author stevenreis
 * @since 1.0 (13/06/24)
 */
@Getter
@JsonInclude(Include.NON_NULL)
public class DataResponse<T> {

    private T data;

    private String message = null;

    public DataResponse(T data, String message, HttpStatus status) {

        this.data = data;
        this.message = message;
    }

    public DataResponse(T data) {
        this.data = data;
    }
}
