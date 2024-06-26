package com.fourdev.webapi.application.http.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourdev.webapi.application.AbstractEndpoint;
import com.fourdev.webapi.domain.tipousuario.TipoUsuario;
import com.fourdev.webapi.infrastructure.dto.tipousuario.TipoUsuarioDto;

/**
 * @author stevenreis
 * @since 1.0 (10/06/24)
 */
@RestController
@RequestMapping("tipousuario")
public class TipoUsuarioEndpoint extends AbstractEndpoint<TipoUsuarioDto, TipoUsuario> {

}
