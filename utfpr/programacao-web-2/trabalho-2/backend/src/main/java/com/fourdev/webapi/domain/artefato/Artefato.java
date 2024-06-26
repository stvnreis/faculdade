package com.fourdev.webapi.domain.artefato;

import com.fourdev.webapi.domain.IDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Getter
@Setter
@Entity
public class Artefato implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String resumo;

    private String urlPdf;

    @Override
    public Long getId() {

        return this.id;
    }
}
