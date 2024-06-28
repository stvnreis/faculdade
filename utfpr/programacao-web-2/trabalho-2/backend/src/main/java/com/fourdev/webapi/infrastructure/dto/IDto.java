package com.fourdev.webapi.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface IDto<T> {

    T getId();
}
