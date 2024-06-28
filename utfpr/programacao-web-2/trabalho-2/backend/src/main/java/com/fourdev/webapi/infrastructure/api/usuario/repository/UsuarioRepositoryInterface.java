package com.fourdev.webapi.infrastructure.api.usuario.repository;

import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.infrastructure.api.RepositoryInterface;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
public interface UsuarioRepositoryInterface extends RepositoryInterface<Usuario> {

    Usuario findByIdExterno(String idExterno);

    Usuario findByUsuario(String usuario);
}
