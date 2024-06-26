package com.fourdev.webapi.infrastructure.implement.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fourdev.webapi.domain.usuario.Usuario;

/**
 * @author stevenreis
 * @since 1.0 (09/05/24)
 */
@Repository
public interface JpaUsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "")
    Usuario findByIdExterno(String idExterno);

    @Override
    @Query(value = "from Usuario u left join UsuarioAtividade ua on ua.usuario.id = u.id left join Atividade a on a.id = ua.atividade.id where u.id = :id")
    Optional<Usuario> findById(Long id);

    @Override
    @Query(value = "from Usuario u left join UsuarioAtividade ua on ua.usuario.id = u.id left join Atividade a on a.id = ua.atividade.id")
    List<Usuario> findAll();
}
