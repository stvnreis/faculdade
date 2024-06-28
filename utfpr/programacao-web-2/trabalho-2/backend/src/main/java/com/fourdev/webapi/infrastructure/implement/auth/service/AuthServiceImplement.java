package com.fourdev.webapi.infrastructure.implement.auth.service;

import static java.util.Objects.isNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.infrastructure.api.auth.service.AuthService;
import com.fourdev.webapi.infrastructure.api.usuario.mapper.UsuarioMapperInterface;
import com.fourdev.webapi.infrastructure.api.usuario.service.UsuarioServiceInterface;
import com.fourdev.webapi.infrastructure.dto.usuario.UsuarioDto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (27/06/24)
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImplement implements AuthService {

    private static final String DS_CHAVE = "123456";

    private final @NonNull UsuarioServiceInterface usuarioService;

    private final @NonNull UsuarioMapperInterface usuarioMapper;

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {

        return usuarioService.findByUsername(username);
    }

    @Override
    public UsuarioDto authenticate(UsuarioDto usuario) {

        if (isNull(usuario.getUsuario()) || isNull(usuario.getSenha())) {
            return null;
        }

        Usuario entity = loadUserByUsername(usuario.getUsuario());

        boolean usuarioValido = usuario.getUsuario().equalsIgnoreCase(entity.getUsuario()) && usuario.getSenha().equals(entity.getSenha());

        String token = null;

        if (usuarioValido) {
            try {
                Algorithm alg = Algorithm.HMAC256(DS_CHAVE);

                token = JWT.create().withIssuer("web-api").withSubject(usuario.getUsuario()).sign(alg);
            } catch (Exception e) {

            }
        }

        var dto = usuarioMapper.convertToDto(entity);
        dto.setAccessToken(token);

        return dto;
    }

    @Override
    public String validateToken(String token) {

        try {

            Algorithm alg = Algorithm.HMAC256(DS_CHAVE);
            return JWT.require(alg)
                    .withIssuer("web-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {

            return "";
        }
    }
}
