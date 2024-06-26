package com.fourdev.webapi.infrastructure.dto.tipousuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fourdev.webapi.infrastructure.dto.IDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (13/05/24)
 */
@Getter
@Setter
public class TipoUsuarioDto implements IDto {

    private String tpUsuario;

    private String descricao;

    @JsonIgnore
    @Override
    public Object getId() {

        return null;
    }
}
