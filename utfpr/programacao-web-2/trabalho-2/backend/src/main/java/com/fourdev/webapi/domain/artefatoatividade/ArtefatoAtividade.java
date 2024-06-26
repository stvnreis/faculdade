package com.fourdev.webapi.domain.artefatoatividade;

import com.fourdev.webapi.domain.IDomain;
import com.fourdev.webapi.domain.artefato.Artefato;
import com.fourdev.webapi.domain.atividade.Atividade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Entity
@Getter
@Setter
public class ArtefatoAtividade implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Artefato artefato;

    @ManyToOne
    private Atividade atividade;

    @Override
    public Long getId() {

        return this.id;
    }
}
