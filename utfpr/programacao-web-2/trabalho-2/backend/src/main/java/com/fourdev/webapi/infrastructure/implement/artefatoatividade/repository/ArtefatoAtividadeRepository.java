package com.fourdev.webapi.infrastructure.implement.artefatoatividade.repository;

import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.artefatoatividade.ArtefatoAtividade;
import com.fourdev.webapi.infrastructure.api.artefatoatividade.repository.ArtefatoAtividadeRepositoryInterface;
import com.fourdev.webapi.infrastructure.implement.RepositoryInterfaceImplement;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Repository
public class ArtefatoAtividadeRepository extends RepositoryInterfaceImplement<ArtefatoAtividade>
        implements ArtefatoAtividadeRepositoryInterface {

}
