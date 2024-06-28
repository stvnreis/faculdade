package com.fourdev.webapi.application.http.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.infrastructure.api.auth.service.AuthService;
import com.fourdev.webapi.infrastructure.dto.usuario.UsuarioDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (27/06/24)
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthEndpoint {

    private final @NonNull AuthService authService;

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody UsuarioDto usuario) {

        UsuarioDto dto = authService.authenticate(usuario);

        return ResponseEntity.ok(dto);
    }
}
