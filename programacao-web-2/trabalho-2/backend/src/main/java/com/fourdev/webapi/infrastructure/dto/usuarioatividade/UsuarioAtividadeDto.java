package com.fourdev.webapi.infrastructure.dto.usuarioatividade;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fourdev.webapi.infrastructure.dto.IDto;
import com.fourdev.webapi.infrastructure.dto.artefato.ArtefatoDto;
import com.fourdev.webapi.infrastructure.dto.atividade.AtividadeDto;
import com.fourdev.webapi.infrastructure.dto.usuario.UsuarioDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Getter
@Setter
public class UsuarioAtividadeDto implements IDto<Long> {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhEntrega;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhEntregaMaximo;

    @JsonIgnore
    private AtividadeDto atividade;

    private UsuarioDto usuario;

    private ArtefatoDto artefato;

    @Override
    public Long getId() {

        return this.id;
    }

    public boolean isEntregue() {

        return nonNull(this.dhEntrega);
    }

    public boolean isEntregueAtrasado() {
        return nonNull(this.dhEntregaMaximo) && isEntregue() && this.dhEntregaMaximo.isBefore(this.dhEntrega);
    }
}
