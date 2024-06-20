package com.fourdev.webapi.infrastructure.api;

import java.util.ArrayList;
import java.util.List;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.infrastructure.dto.IDto;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
public interface MapperInterface<T extends IDomain, K extends IDto> {

    K convertToDto(T domain);

    T convertToDomain(K dto);

    default List<K> convertListToDto(List<T> domainList) {
        List<K> dtoList = new ArrayList<>();
        for (T domain : domainList) {
            dtoList.add(convertToDto(domain));
        }
        return dtoList;
    }

    default List<T> convertListToDomain(List<K> dtoList) {
        List<T> domainList = new ArrayList<>();
        for (K dto : dtoList) {
            domainList.add(convertToDomain(dto));
        }
        return domainList;
    }
}
