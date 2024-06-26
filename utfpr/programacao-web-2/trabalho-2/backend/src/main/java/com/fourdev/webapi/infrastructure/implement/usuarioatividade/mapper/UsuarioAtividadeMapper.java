package com.fourdev.webapi.infrastructure.implement.usuarioatividade.mapper;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fourdev.webapi.domain.usuarioatividade.UsuarioAtividade;
import com.fourdev.webapi.infrastructure.api.artefato.mapper.ArtefatoMapperInterface;
import com.fourdev.webapi.infrastructure.api.usuario.mapper.UsuarioMapperInterface;
import com.fourdev.webapi.infrastructure.api.usuarioatividade.mapper.UsuarioAtividadeMapperInterface;
import com.fourdev.webapi.infrastructure.dto.usuarioatividade.UsuarioAtividadeDto;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioAtividadeMapper implements UsuarioAtividadeMapperInterface {

//    private final AtividadeMapperInterface atividadeMapper;

    private final UsuarioMapperInterface usuarioMapperInterface;

    private final ArtefatoMapperInterface artefatoMapper;

    @Override
    public UsuarioAtividadeDto convertToDto(UsuarioAtividade domain) {

        if (isNull(domain)) {
            return null;
        }

        UsuarioAtividadeDto dto = new UsuarioAtividadeDto();
        dto.setId(domain.getId());
        dto.setDhEntregaMaximo(domain.getDhEntregaLimite());
        dto.setDhEntrega(domain.getDhEntrega());
//        dto.setAtividade(atividadeMapper.convertToDto(domain.getAtividade()));

        dto.setUsuario(usuarioMapperInterface.convertToDto(domain.getUsuario()));
        dto.setArtefato(artefatoMapper.convertToDto(domain.getArtefato()));

        return dto;
    }

    @Override
    public UsuarioAtividade convertToDomain(UsuarioAtividadeDto dto) {

        if (isNull(dto)) {
            return null;
        }

        UsuarioAtividade domain = new UsuarioAtividade();
        domain.setId(dto.getId());
        domain.setDhEntrega(dto.getDhEntrega());

//        if (nonNull(dto.getAtividade())) {
//            domain.setAtividade(atividadeMapper.convertToDomain(dto.getAtividade()));
//        }
        domain.setDhEntregaLimite(dto.getDhEntregaMaximo());

        if (nonNull(dto.getUsuario())) {

            domain.setUsuario(usuarioMapperInterface.convertToDomain(dto.getUsuario()));
        }

        domain.setArtefato(artefatoMapper.convertToDomain(dto.getArtefato()));

        return domain;
    }
}
