package com.fourdev.webapi.infrastructure.implement.tipousuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourdev.webapi.domain.tipousuario.TipoUsuario;
import com.fourdev.webapi.infrastructure.api.tipousuario.repository.TipoUsuarioRepositoryInterface;
import com.fourdev.webapi.infrastructure.api.tipousuario.service.TipoUsuarioServiceInterface;
import com.fourdev.webapi.infrastructure.implement.ServiceInterfaceImplement;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (13/05/24)
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TipoUsuarioService extends ServiceInterfaceImplement<TipoUsuario>
        implements TipoUsuarioServiceInterface {

    private final TipoUsuarioRepositoryInterface tipoUsuarioRepository;

    @Override
    public TipoUsuario findByTipoUsuario(String tipoUsuario) {

        return tipoUsuarioRepository.findByTipoUsuario(tipoUsuario);
    }
}
