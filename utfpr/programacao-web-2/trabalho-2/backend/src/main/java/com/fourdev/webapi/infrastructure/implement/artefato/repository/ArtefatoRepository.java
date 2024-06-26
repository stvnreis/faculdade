package com.fourdev.webapi.infrastructure.implement.artefato.repository;

import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.artefato.Artefato;
import com.fourdev.webapi.infrastructure.api.artefato.repository.ArtefatoRepositoryInterface;
import com.fourdev.webapi.infrastructure.implement.RepositoryInterfaceImplement;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Repository
public class ArtefatoRepository extends RepositoryInterfaceImplement<Artefato>
        implements ArtefatoRepositoryInterface {

    @Override
    public Artefato insert(Artefato entity) {

        return super.insert(entity);
    }
}
