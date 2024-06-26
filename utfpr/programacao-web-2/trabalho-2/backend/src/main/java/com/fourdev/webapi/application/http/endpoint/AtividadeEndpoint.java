package com.fourdev.webapi.application.http.endpoint;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fourdev.webapi.application.AbstractEndpoint;
import com.fourdev.webapi.domain.atividade.Atividade;
import com.fourdev.webapi.infrastructure.api.artefato.mapper.ArtefatoMapperInterface;
import com.fourdev.webapi.infrastructure.api.atividade.service.AtividadeServiceInterface;
import com.fourdev.webapi.infrastructure.api.usuarioatividade.mapper.UsuarioAtividadeMapperInterface;
import com.fourdev.webapi.infrastructure.dto.artefato.ArtefatoDto;
import com.fourdev.webapi.infrastructure.dto.atividade.AtividadeDto;
import com.fourdev.webapi.infrastructure.dto.usuarioatividade.UsuarioAtividadeDto;

import lombok.RequiredArgsConstructor;

/**
 * @author stevenreis
 * @since 1.0 (10/05/24)
 */
@RestController
@RequestMapping("atividade")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtividadeEndpoint extends AbstractEndpoint<AtividadeDto, Atividade> {

    private final AtividadeServiceInterface atividadeService;

    private final UsuarioAtividadeMapperInterface usuarioAtividadeMapper;

    private final ArtefatoMapperInterface artefatoAtividadeMapper;

    @PostMapping("{id}/atribuirusuario")
    public ResponseEntity atribuirUsuario(@PathVariable("id") Long id, @RequestBody UsuarioAtividadeDto usuarioAtividade) {

        atividadeService.atribuirUsuario(id, usuarioAtividadeMapper.convertToDomain(usuarioAtividade));

        return ok(getDataWithMessage(null, "Usuario atribuido com sucesso"));
    }

    @PostMapping("{id}/adicionarartefato")
    public ResponseEntity adicionarArtefato(@PathVariable Long id, @RequestBody ArtefatoDto artefatoDto) {

        atividadeService.adicionarArtefato(id, artefatoAtividadeMapper.convertToDomain(artefatoDto));

        return ok();
    }

    @PostMapping("{id}/entregar")
    public ResponseEntity entregarAtividade(@PathVariable("id") Long idAtividade, @RequestParam Map<String, String> allRequestParams, @RequestBody ArtefatoDto artefato) {

        atividadeService.entregarAtividade(idAtividade, allRequestParams.get("idUsuario"), artefatoAtividadeMapper.convertToDomain(artefato));

        return ok();
    }
}
