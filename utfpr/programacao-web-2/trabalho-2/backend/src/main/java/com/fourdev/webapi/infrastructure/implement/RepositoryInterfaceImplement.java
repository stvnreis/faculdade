package com.fourdev.webapi.infrastructure.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.infrastructure.api.RepositoryInterface;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
public class RepositoryInterfaceImplement<T extends IDomain> implements RepositoryInterface<T> {

    @Autowired(required = false)
    protected JpaRepository<T, Long> jpaRepository;

    @Override
    public List<T> findAll() {

        return jpaRepository.findAll();
    }

    @Override
    public T findById(Long id) {

        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(T entity) {
        jpaRepository.save(entity);
    }

    @Override
    public void saveMany(List<T> entities) {
        jpaRepository.saveAll(entities);
    }

    @Override
    public T insert(T entity) {

        return jpaRepository.save(entity);
    }

    @Override
    public T update(T entity) {

        return jpaRepository.save(entity);
    }

    @Override
    public void delete(T entity) {

        jpaRepository.delete(entity);
    }
}
