package com.fourdev.webapi.application.usuario.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourdev.webapi.application.AbstractEndpoint;
import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.infrastructure.dto.usuario.UsuarioDto;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
@RestController
@RequestMapping("usuario")
public class UsuarioEndpoint extends AbstractEndpoint<UsuarioDto, Usuario> {
}
