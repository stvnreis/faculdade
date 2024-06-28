package com.fourdev.webapi.infrastructure.api.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fourdev.webapi.infrastructure.dto.usuario.UsuarioDto;

/**
 * @author stevenreis
 * @since 1.0 (27/06/24)
 */
public interface AuthService extends UserDetailsService {

    UsuarioDto authenticate(UsuarioDto usuario);

    String validateToken(String token);
}
