package com.fourdev.webapi.infrastructure.implement.atividade.repository;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.atividade.Atividade;
import com.fourdev.webapi.infrastructure.api.artefato.repository.ArtefatoRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.artefatoatividade.repository.ArtefatoAtividadeRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.atividade.repository.AtividadeRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.usuarioatividade.repository.UsuarioAtividadeRepositoryInterface;
import com.fourdev.webapi.infrastructure.implement.RepositoryInterfaceImplement;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtividadeRepositoryImplement extends RepositoryInterfaceImplement<Atividade>
        implements AtividadeRepositoryInterface {

    private final ArtefatoAtividadeRepositoryInterface artefatoAtividadeRepository;

    private final UsuarioAtividadeRepositoryInterface usuarioAtividadeRepository;

    @Override
    public Atividade insert(Atividade entity) {

        super.insert(entity);

        if (isNotEmpty(entity.getArtefatosAtividade())) {
            entity.getArtefatosAtividade().forEach(artefato -> {

                artefato.setAtividade(entity);
                artefatoAtividadeRepository.insert(artefato);
            });
        }

        return entity;
    }

    @Override
    public Atividade update(Atividade entity) {

        usuarioAtividadeRepository.saveMany(entity.getUsuarioAtividades());

        super.update(entity);

        return entity;
    }
}
