package com.fourdev.webapi.infrastructure.implement.atividade.mapper;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fourdev.webapi.domain.atividade.Atividade;
import com.fourdev.webapi.infrastructure.api.artefatoatividade.mapper.ArtefatoAtividadeMapperInterface;
import com.fourdev.webapi.infrastructure.api.atividade.mapper.AtividadeMapperInterface;
import com.fourdev.webapi.infrastructure.api.usuarioatividade.mapper.UsuarioAtividadeMapperInterface;
import com.fourdev.webapi.infrastructure.dto.atividade.AtividadeDto;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtividadeMapperImplement implements AtividadeMapperInterface {

    private final ArtefatoAtividadeMapperInterface artefatoAtividadeMapper;

    private final UsuarioAtividadeMapperInterface usuarioAtividadeMapper;

    @Override
    public AtividadeDto convertToDto(Atividade domain) {

        if (isNull(domain)) {
            return null;
        }

        AtividadeDto dto = new AtividadeDto();
        dto.setId(domain.getId());
        dto.setDescricao(domain.getDescricao());
        dto.setTitulo(domain.getTitulo());
        dto.setDhCriacao(domain.getDhCriacao());

        if (isNotEmpty(domain.getArtefatosAtividade())) {
            dto.setArtefatos(artefatoAtividadeMapper.convertListToDto(domain.getArtefatosAtividade()));
        }

        if (isNotEmpty(domain.getUsuarioAtividades())) {
            dto.setUsuariosAtividade(usuarioAtividadeMapper.convertListToDto(domain.getUsuarioAtividades()));
        }

        return dto;
    }

    @Override
    public Atividade convertToDomain(AtividadeDto dto) {

        if (isNull(dto)) {
            return null;
        }

        Atividade atividade = new Atividade();
        atividade.setId(dto.getId());
        atividade.setDescricao(dto.getDescricao());
        atividade.setTitulo(dto.getTitulo());
        atividade.setDhCriacao(isNull(dto.getDhCriacao()) ? LocalDateTime.now() : dto.getDhCriacao());

        if (isNotEmpty(dto.getArtefatos())) {
            atividade.setArtefatosAtividade(artefatoAtividadeMapper.convertListToDomain(dto.getArtefatos()));
        }

        if (isNotEmpty(dto.getUsuariosAtividade())) {
            atividade.setUsuarioAtividades(usuarioAtividadeMapper.convertListToDomain(dto.getUsuariosAtividade()));
        }

        return atividade;
    }
}
