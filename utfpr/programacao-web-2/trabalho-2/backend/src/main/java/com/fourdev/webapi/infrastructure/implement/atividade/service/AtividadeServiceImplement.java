package com.fourdev.webapi.infrastructure.implement.atividade.service;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourdev.webapi.domain.artefato.Artefato;
import com.fourdev.webapi.domain.atividade.Atividade;
import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.domain.usuarioatividade.UsuarioAtividade;
import com.fourdev.webapi.infrastructure.api.artefatoatividade.repository.ArtefatoAtividadeRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.atividade.repository.AtividadeRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.atividade.service.AtividadeServiceInterface;
import com.fourdev.webapi.infrastructure.api.usuario.repository.UsuarioRepositoryInterface;
import com.fourdev.webapi.infrastructure.implement.ServiceInterfaceImplement;
import com.fourdev.webapi.infrastructure.implement.usuarioatividade.repository.UsuarioAtividadeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtividadeServiceImplement extends ServiceInterfaceImplement<Atividade>
        implements AtividadeServiceInterface {

    private final AtividadeRepositoryInterface atividadeRepository;

    private final ArtefatoAtividadeRepositoryInterface artefatoAtividadeRepository;

    private final UsuarioRepositoryInterface usuarioRepository;

    private final UsuarioAtividadeRepository usuarioAtividadeRepository;

    @Override
    public void adicionarArtefato(Long idAtividade, Artefato artefato) {

        Atividade atividade = this.atividadeRepository.findById(idAtividade);
        atividade.addArtefato(artefato);

        this.artefatoAtividadeRepository.saveMany(atividade.getArtefatosAtividade());
    }

    @Override
    public void atribuirUsuario(Long idAtividade, UsuarioAtividade usuarioAtividade) {

        Atividade atividade = atividadeRepository.findById(idAtividade);
        atividade.atribuirParaUsuario(usuarioAtividade);

        atividadeRepository.update(atividade);
    }

    @Override
    @Transactional
    public void entregarAtividade(Long idAtividade, Long idUsuario, Artefato artefato) {

        Atividade atividade = atividadeRepository.findById(idAtividade);
        Usuario usuario = usuarioRepository.findById(idUsuario);

        UsuarioAtividade usuarioAtividade = getUsuarioAtividade(atividade, usuario);

        if (isNull(usuarioAtividade)) {
            usuarioAtividade = new UsuarioAtividade();

            usuarioAtividade.setAtividade(atividade);
            usuarioAtividade.setUsuario(usuario);
            usuarioAtividade.setArtefato(artefato);
            usuarioAtividade.setDhEntrega(LocalDateTime.now());
        }

        usuarioAtividade.entregarAtividade(artefato);

        usuarioAtividadeRepository.save(usuarioAtividade);
    }

    private UsuarioAtividade getUsuarioAtividade(Atividade atividade, Usuario usuario) {

        return atividade.getUsuarioAtividades()
                .stream()
                .filter(item -> item.getUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }
}
