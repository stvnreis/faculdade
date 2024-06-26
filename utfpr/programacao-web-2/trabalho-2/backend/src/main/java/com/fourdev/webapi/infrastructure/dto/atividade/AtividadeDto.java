package com.fourdev.webapi.infrastructure.dto.atividade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fourdev.webapi.infrastructure.dto.IDto;
import com.fourdev.webapi.infrastructure.dto.artefato.ArtefatoDto;
import com.fourdev.webapi.infrastructure.dto.usuarioatividade.UsuarioAtividadeDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Getter
@Setter
public class AtividadeDto implements IDto<Long> {

    private Long id;

    private String titulo;

    private String descricao;

    private ArtefatoDto artefato;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhCriacao;

    private LocalDate dtEntregaMaxima;

    private List<UsuarioAtividadeDto> usuariosAtividade;

    @Override
    public Long getId() {

        return this.id;
    }
}
