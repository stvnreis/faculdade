package com.fourdev.webapi.infrastructure.implement.artefatoatividade.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fourdev.webapi.domain.artefatoatividade.ArtefatoAtividade;
import com.fourdev.webapi.infrastructure.api.artefato.mapper.ArtefatoMapperInterface;
import com.fourdev.webapi.infrastructure.api.artefatoatividade.mapper.ArtefatoAtividadeMapperInterface;
import com.fourdev.webapi.infrastructure.dto.artefato.ArtefatoDto;
import com.fourdev.webapi.infrastructure.dto.artefatoatividade.ArtefatoAtividadeDto;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtefatoAtividadeMapper implements ArtefatoAtividadeMapperInterface {

    private final ArtefatoMapperInterface artefatoMapper;

    @Override
    public ArtefatoDto convertToDto(ArtefatoAtividade domain) {

        return artefatoMapper.convertToDto(domain.getArtefato());
    }

    @Override
    public ArtefatoAtividade convertToDomain(ArtefatoDto dto) {

        ArtefatoAtividade domain = new ArtefatoAtividade();

        domain.setArtefato(artefatoMapper.convertToDomain(dto));

        return domain;
    }
}
