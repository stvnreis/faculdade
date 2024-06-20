package com.fourdev.webapi.infrastructure.implement.usuarioatividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.usuarioatividade.UsuarioAtividade;

/**
 * @author stevenreis
 * @since 1.0 (11/05/24)
 */
@Repository
public interface JpaUsuarioAtividadeRepository extends JpaRepository<UsuarioAtividade, Long> {

}
