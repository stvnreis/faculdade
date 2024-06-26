package com.fourdev.webapi.infrastructure.implement.tipousuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.tipousuario.TipoUsuario;

/**
 * @author stevenreis
 * @since 1.0 (13/05/24)
 */
@Repository
public interface JpaTipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    @Query(value = "from TipoUsuario tu where tu.tpUsuario = :tpUsuario")
    Optional<TipoUsuario> findByTipoUsuario(String tpUsuario);
}
