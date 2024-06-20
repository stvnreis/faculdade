package com.fourdev.webapi.infrastructure.api.atividade.service;

import com.fourdev.webapi.domain.artefato.Artefato;
import com.fourdev.webapi.domain.atividade.Atividade;
import com.fourdev.webapi.domain.usuarioatividade.UsuarioAtividade;
import com.fourdev.webapi.infrastructure.api.ServiceInterface;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
public interface AtividadeServiceInterface extends ServiceInterface<Atividade> {

    void adicionarArtefato(Long idAtividade, Artefato artefato);

    void atribuirUsuario(Long idAtividade, UsuarioAtividade usuario);

    void entregarAtividade(Long idAtividade, String idExternoUsuario, Artefato artefato);
}
