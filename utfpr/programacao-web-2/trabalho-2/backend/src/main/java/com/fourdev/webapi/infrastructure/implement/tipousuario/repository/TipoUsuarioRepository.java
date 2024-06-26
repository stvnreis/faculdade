package com.fourdev.webapi.infrastructure.implement.tipousuario.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.tipousuario.TipoUsuario;
import com.fourdev.webapi.infrastructure.api.tipousuario.repository.TipoUsuarioRepositoryInterface;
import com.fourdev.webapi.infrastructure.implement.RepositoryInterfaceImplement;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (13/05/24)
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TipoUsuarioRepository extends RepositoryInterfaceImplement<TipoUsuario>
        implements TipoUsuarioRepositoryInterface {

    private final JpaTipoUsuarioRepository jpaTipoUsuarioRepository;

    @Override
    public TipoUsuario findByTipoUsuario(String tipoUsuario) {

        Optional<TipoUsuario> entity = jpaTipoUsuarioRepository.findByTipoUsuario(tipoUsuario);

        return entity.orElse(null);
    }
}
