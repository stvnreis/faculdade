package com.fourdev.webapi.infrastructure.implement.artefatoatividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.artefatoatividade.ArtefatoAtividade;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Repository
public interface JpaArtefatoAtividadeRepository extends JpaRepository<ArtefatoAtividade, Long> {

}
