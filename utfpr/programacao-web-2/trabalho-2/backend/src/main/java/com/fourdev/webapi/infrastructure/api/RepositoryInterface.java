package com.fourdev.webapi.infrastructure.api;

import java.util.List;

import com.fourdev.webapi.domain.IDomain;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
public interface RepositoryInterface<T extends IDomain> {

    List<T> findAll();

    T findById(Long id);

    T insert(T entity);

    T update(T entity);

    void save(T entity);

    void saveMany(List<T> entities);

    void delete(T entity);
}
