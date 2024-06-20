package com.fourdev.webapi.infrastructure.implement.artefato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.artefato.Artefato;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Repository
public interface JpaArtefatoRepository extends JpaRepository<Artefato, Long> {

}
