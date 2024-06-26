package com.fourdev.webapi.domain.atividade;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.domain.artefato.Artefato;
import com.fourdev.webapi.domain.artefatoatividade.ArtefatoAtividade;
import com.fourdev.webapi.domain.usuarioatividade.UsuarioAtividade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Entity
@Getter
@Setter
public class Atividade implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private LocalDateTime dhCriacao;

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL)
    private List<UsuarioAtividade> usuarioAtividades;

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL)
    private List<ArtefatoAtividade> artefatosAtividade;

    @Override
    public Long getId() {

        return this.id;
    }

    public void atribuirParaUsuario(UsuarioAtividade usuarioAtividade) {

        usuarioAtividade.setAtividade(this);
        this.usuarioAtividades.add(usuarioAtividade);
    }

    public void addArtefato(Artefato artefato) {

        ArtefatoAtividade artefatoAtividade = new ArtefatoAtividade();
        artefatoAtividade.setArtefato(artefato);
        artefatoAtividade.setAtividade(this);

        this.artefatosAtividade.add(artefatoAtividade);
    }

    public void setArtefatosAtividade(List<ArtefatoAtividade> artefatosAtividade) {

        if (isEmpty(this.artefatosAtividade)) {
            this.artefatosAtividade = new ArrayList<>();
        }

        for (ArtefatoAtividade artefato : artefatosAtividade) {
            artefato.setAtividade(this);
            this.artefatosAtividade.add(artefato);
        }
    }

    public void setUsuarioAtividades(List<UsuarioAtividade> usuariosAtividade) {

        if (isEmpty(this.usuarioAtividades)) {
            this.usuarioAtividades = new ArrayList<>();
        }

        for (UsuarioAtividade usuario : usuariosAtividade) {
            usuario.setAtividade(this);
            this.usuarioAtividades.add(usuario);
        }
    }
}
