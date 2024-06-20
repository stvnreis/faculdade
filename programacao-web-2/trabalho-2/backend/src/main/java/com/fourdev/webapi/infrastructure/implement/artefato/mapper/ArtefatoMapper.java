package com.fourdev.webapi.infrastructure.implement.artefato.mapper;

import static java.util.Objects.isNull;

import org.springframework.stereotype.Component;

import com.fourdev.webapi.domain.artefato.Artefato;
import com.fourdev.webapi.infrastructure.api.artefato.mapper.ArtefatoMapperInterface;
import com.fourdev.webapi.infrastructure.dto.artefato.ArtefatoDto;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Component
public class ArtefatoMapper implements ArtefatoMapperInterface {

    @Override
    public ArtefatoDto convertToDto(Artefato domain) {

        if (isNull(domain)) {
            return null;
        }

        ArtefatoDto dto = new ArtefatoDto();
        dto.setId(domain.getId());
        dto.setResumo(domain.getResumo());
        dto.setTitulo(domain.getTitulo());
        dto.setUrlPdf(domain.getUrlPdf());

        return dto;
    }

    @Override
    public Artefato convertToDomain(ArtefatoDto dto) {

        if (isNull(dto)) {
            return null;
        }

        Artefato domain = new Artefato();
        domain.setId(dto.getId());
        domain.setResumo(dto.getResumo());
        domain.setTitulo(dto.getTitulo());
        domain.setUrlPdf(dto.getUrlPdf());

        return domain;
    }
}
