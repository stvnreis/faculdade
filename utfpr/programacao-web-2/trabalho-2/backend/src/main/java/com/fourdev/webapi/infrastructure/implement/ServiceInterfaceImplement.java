package com.fourdev.webapi.infrastructure.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.infrastructure.api.RepositoryInterface;
import com.fourdev.webapi.infrastructure.api.ServiceInterface;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
public class ServiceInterfaceImplement<T extends IDomain> implements ServiceInterface<T> {

    @Autowired(required=false)
    protected RepositoryInterface<T> abstractRepository;

    @Override
    public List<T> findAll() {

        return this.abstractRepository.findAll();
    }

    @Override
    public T findById(Long id) {

        return abstractRepository.findById(id);
    }

    @Override
    public T insert(T entity) {

        return this.abstractRepository.insert(entity);
    }

    @Override
    public T update(T entity) {

        return this.abstractRepository.update(entity);
    }
}
