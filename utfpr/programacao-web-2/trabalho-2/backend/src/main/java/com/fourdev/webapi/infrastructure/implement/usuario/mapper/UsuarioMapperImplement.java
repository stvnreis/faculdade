package com.fourdev.webapi.infrastructure.implement.usuario.mapper;

import static java.util.Objects.isNull;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fourdev.webapi.domain.usuario.Usuario;
import com.fourdev.webapi.infrastructure.api.tipousuario.mapper.TipoUsuarioMapperInterface;
import com.fourdev.webapi.infrastructure.api.tipousuario.service.TipoUsuarioServiceInterface;
import com.fourdev.webapi.infrastructure.api.usuario.mapper.UsuarioMapperInterface;
import com.fourdev.webapi.infrastructure.dto.usuario.UsuarioDto;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioMapperImplement implements UsuarioMapperInterface {

    private final TipoUsuarioServiceInterface tipoUsuarioService;

    private final TipoUsuarioMapperInterface tipoUsuarioMapper;

    @Override
    public UsuarioDto convertToDto(Usuario domain) {

        if (isNull(domain)) {
            return null;
        }

        UsuarioDto dto = new UsuarioDto();
        dto.setNome(domain.getNome());
        dto.setUsuario(domain.getUsuario());
//        dto.setIdExterno(domain.getIdExterno());
        dto.setId(domain.getId());
        dto.setEmail(domain.getEmail());
        dto.setTipoUsuario(tipoUsuarioMapper.convertToDto(domain.getTipoUsuario()));

        return dto;
    }

    @Override
    public Usuario convertToDomain(UsuarioDto dto) {

        if (isNull(dto)) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setUsuario(dto.getUsuario());
        usuario.setIdExterno(isNull(dto.getIdExterno()) ? UUID.randomUUID().toString() : dto.getIdExterno());
        usuario.setSenha(dto.getSenha());
        usuario.setEmail(dto.getEmail());
        usuario.setTipoUsuario(tipoUsuarioService.findByTipoUsuario(dto.getTpUsuario()));

        return usuario;
    }
}
