package com.fourdev.webapi.infrastructure.api.tipousuario.repository;

import com.fourdev.webapi.domain.tipousuario.TipoUsuario;
import com.fourdev.webapi.infrastructure.api.RepositoryInterface;

/**
 * @author stevenreis
 * @since 1.0 (13/05/24)
 */
public interface TipoUsuarioRepositoryInterface extends RepositoryInterface<TipoUsuario> {

    TipoUsuario findByTipoUsuario(String tipoUsuario);
}
