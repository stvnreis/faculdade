package com.fourdev.webapi.domain.usuarioatividade;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.domain.artefato.Artefato;
import com.fourdev.webapi.domain.atividade.Atividade;
import com.fourdev.webapi.domain.usuario.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Entity
@Getter
@Setter
public class UsuarioAtividade implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_atividade")
    private Atividade atividade;

    private LocalDateTime dhEntregaLimite;

    private LocalDateTime dhEntrega;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_artefato")
    private Artefato artefato;

    @Override
    public Long getId() {

        return this.id;
    }

    public boolean isEntregue() {

        return nonNull(dhEntrega);
    }

    public void entregarAtividade(Artefato artefato) {

        this.dhEntrega = LocalDateTime.now();
        this.artefato = artefato;
    }
}
