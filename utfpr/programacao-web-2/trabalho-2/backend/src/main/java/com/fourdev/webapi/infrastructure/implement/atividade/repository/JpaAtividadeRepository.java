package com.fourdev.webapi.infrastructure.implement.atividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.atividade.Atividade;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@Repository
public interface JpaAtividadeRepository extends JpaRepository<Atividade, Long> {

}
