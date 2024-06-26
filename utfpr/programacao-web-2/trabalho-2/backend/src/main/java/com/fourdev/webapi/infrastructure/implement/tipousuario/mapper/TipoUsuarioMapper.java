package com.fourdev.webapi.infrastructure.implement.tipousuario.mapper;

import static java.util.Objects.isNull;

import org.springframework.stereotype.Component;

import com.fourdev.webapi.domain.tipousuario.TipoUsuario;
import com.fourdev.webapi.infrastructure.api.tipousuario.mapper.TipoUsuarioMapperInterface;
import com.fourdev.webapi.infrastructure.dto.tipousuario.TipoUsuarioDto;

/**
 * @author stevenreis
 * @since 1.0 (14/05/24)
 */
@Component
public class TipoUsuarioMapper implements TipoUsuarioMapperInterface {

    @Override
    public TipoUsuarioDto convertToDto(TipoUsuario tipoUsuario) {

        if (isNull(tipoUsuario)) {
            return null;
        }

        TipoUsuarioDto tipoUsuarioDto = new TipoUsuarioDto();
        tipoUsuarioDto.setTpUsuario(tipoUsuario.getTpUsuario());
        tipoUsuarioDto.setDescricao(tipoUsuario.getDescricao());

        return tipoUsuarioDto;
    }

    @Override
    public TipoUsuario convertToDomain(TipoUsuarioDto tipoUsuarioDto) {

        if (isNull(tipoUsuarioDto)) {
            return null;
        }

        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setTpUsuario(tipoUsuarioDto.getTpUsuario());
        tipoUsuario.setDescricao(tipoUsuarioDto.getDescricao());

        return tipoUsuario;
    }
}
