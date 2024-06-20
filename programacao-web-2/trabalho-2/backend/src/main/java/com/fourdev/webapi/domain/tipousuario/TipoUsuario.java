package com.fourdev.webapi.domain.tipousuario;

import java.util.List;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.domain.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (13/05/24)
 */
@Entity
@Getter
@Setter
public class TipoUsuario implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tpUsuario;

    private String descricao;

    @OneToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios;

    @Override
    public Long getId() {

        return this.id;
    }
}
