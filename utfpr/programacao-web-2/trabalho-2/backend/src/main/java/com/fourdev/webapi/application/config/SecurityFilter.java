package com.fourdev.webapi.application.config;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.infrastructure.api.auth.service.AuthService;
import com.fourdev.webapi.infrastructure.api.usuario.service.UsuarioServiceInterface;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (27/06/24)
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityFilter extends OncePerRequestFilter {

    private final @NonNull AuthService authService;

    private final @NonNull UsuarioServiceInterface usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = getToken(request);

        if (nonNull(token)) {

            String usuario = authService.validateToken(token);

            if (usuario.equals("")) {
                return;
            }

            Usuario domain = usuarioService.findByUsername(usuario);

            var authentication = new UsernamePasswordAuthenticationToken(domain, null, domain.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        return isNull(authorization) ? null : authorization.replace("Bearer ", "");
    }
}
