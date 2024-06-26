package com.fourdev.webapi.infrastructure.implement.usuarioatividade.repository;

import static java.util.Objects.isNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.domain.usuarioatividade.UsuarioAtividade;
import com.fourdev.webapi.infrastructure.api.usuario.repository.UsuarioRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.usuarioatividade.repository.UsuarioAtividadeRepositoryInterface;
import com.fourdev.webapi.infrastructure.implement.RepositoryInterfaceImplement;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioAtividadeRepository extends RepositoryInterfaceImplement<UsuarioAtividade>
        implements UsuarioAtividadeRepositoryInterface {

    private final UsuarioRepositoryInterface usuarioRepository;

    @Override
    public void saveMany(List<UsuarioAtividade> usuarioAtividades) {

        if (isNull(usuarioAtividades)) {
            return;
        }

        for (UsuarioAtividade usuarioAtividade : usuarioAtividades) {
            Usuario usuario = usuarioRepository.findByIdExterno(usuarioAtividade.getUsuario().getIdExterno());
            usuarioAtividade.setUsuario(usuario);

            jpaRepository.save(usuarioAtividade);
        }
    }
}
