package com.fourdev.webapi.infrastructure.implement.usuario.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.infrastructure.api.usuario.repository.UsuarioRepositoryInterface;
import com.fourdev.webapi.infrastructure.implement.RepositoryInterfaceImplement;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioRepository extends RepositoryInterfaceImplement<Usuario>
        implements UsuarioRepositoryInterface {

    private final JpaUsuarioRepository jpaUsuarioRepository;

    @Override
    public Usuario findByIdExterno(String idExterno) {

        return jpaUsuarioRepository.findByIdExterno(idExterno);
    }
}
