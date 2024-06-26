package com.fourdev.webapi.infrastructure.dto.artefato;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fourdev.webapi.infrastructure.dto.IDto;
import com.fourdev.webapi.infrastructure.dto.atividade.AtividadeDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Getter
@Setter
public class ArtefatoDto implements IDto<Long> {

    private Long id;

    private String titulo;

    private String resumo;

    private String urlPdf;

    @JsonIgnore
    private AtividadeDto atividade;

    @Override
    public Long getId() {

        return this.id;
    }
}
