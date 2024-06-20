package com.fourdev.webapi.infrastructure.api.tipousuario.service;

import com.fourdev.webapi.domain.tipousuario.TipoUsuario;
import com.fourdev.webapi.infrastructure.api.ServiceInterface;

/**
 * @author stevenreis
 * @since 1.0 (13/05/24)
 */
public interface TipoUsuarioServiceInterface extends ServiceInterface<TipoUsuario> {

    TipoUsuario findByTipoUsuario(String tipoUsuario);
}
