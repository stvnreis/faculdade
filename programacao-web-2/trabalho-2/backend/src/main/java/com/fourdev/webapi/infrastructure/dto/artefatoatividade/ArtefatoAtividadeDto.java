package com.fourdev.webapi.infrastructure.dto.artefatoatividade;

import com.fourdev.webapi.infrastructure.dto.IDto;
import com.fourdev.webapi.infrastructure.dto.artefato.ArtefatoDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Getter
@Setter
public class ArtefatoAtividadeDto implements IDto<Long> {

    private Long id;

    private ArtefatoDto artefato;

    @Override
    public Long getId() {

        return this.id;
    }
}
