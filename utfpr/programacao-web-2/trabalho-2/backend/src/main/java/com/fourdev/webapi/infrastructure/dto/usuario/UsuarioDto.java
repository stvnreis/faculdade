package com.fourdev.webapi.infrastructure.dto.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fourdev.webapi.infrastructure.dto.IDto;
import com.fourdev.webapi.infrastructure.dto.tipousuario.TipoUsuarioDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
@Getter
@Setter
public class UsuarioDto implements IDto<Long> {

    private Long id;

    @JsonIgnore
    private String idExterno;

    private String nome;

    private String usuario;

    private String email;

    private TipoUsuarioDto tipoUsuario;

    private String tpUsuario;

    private String accessToken;

//    @JsonIgnore
    private String senha;

    @Override
    public Long getId() {

        return this.id;
    }

    @JsonProperty
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public void setSenha(String senha) {

        this.senha = senha;
    }

    @JsonIgnore
    public String getSenha() {
        return senha;
    }
}
