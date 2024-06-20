package com.fourdev.webapi.infrastructure.api.usuarioatividade.repository;

import java.util.List;

import com.fourdev.webapi.domain.usuarioatividade.UsuarioAtividade;
import com.fourdev.webapi.infrastructure.api.RepositoryInterface;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
public interface UsuarioAtividadeRepositoryInterface extends RepositoryInterface<UsuarioAtividade> {

    void saveMany(List<UsuarioAtividade> usuarioAtividades);
}
