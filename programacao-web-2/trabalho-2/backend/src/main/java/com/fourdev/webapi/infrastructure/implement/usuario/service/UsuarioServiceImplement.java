package com.fourdev.webapi.infrastructure.implement.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourdev.webapi.domain.atividade.Atividade;
import com.fourdev.webapi.domain.tipousuario.TipoUsuario;
import com.fourdev.webapi.infrastructure.api.atividade.service.AtividadeServiceInterface;
import com.fourdev.webapi.infrastructure.api.tipousuario.service.TipoUsuarioServiceInterface;
import com.fourdev.webapi.infrastructure.api.usuario.repository.UsuarioRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.usuario.service.UsuarioServiceInterface;
import com.fourdev.webapi.infrastructure.implement.ServiceInterfaceImplement;
import com.fourdev.webapi.domain.usuario.Usuario;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioServiceImplement extends ServiceInterfaceImplement<Usuario>
        implements UsuarioServiceInterface {

    private final TipoUsuarioServiceInterface tipoUsuarioService;

    @Override
    public Usuario insert(Usuario entity) {

        TipoUsuario tipoUsuario = tipoUsuarioService.findByTipoUsuario(entity.getTipoUsuario().getTpUsuario());

        entity.setTipoUsuario(tipoUsuario);

        return super.insert(entity);
    }
}
