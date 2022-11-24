package br.rigolao.desafio_4_etapa_backend.cientistas.controllers;

import br.rigolao.desafio_4_etapa_backend.autenticacao.services.AutenticacaoService;
import br.rigolao.desafio_4_etapa_backend.cientistas.services.CientistasService;
import br.rigolao.desafio_4_etapa_backend.cientistas.utils.CientistaUtil;
import br.rigolao.desafio_4_etapa_backend.config.security.utils.JwtTokenUtil;
import br.rigolao.desafio_4_etapa_backend.dtos.*;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("cientistas")
public class CientistasController {

    private final CientistasService cientistasService;

    private final AutenticacaoService autenticacaoService;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public CientistasController(CientistasService cientistasService, AutenticacaoService autenticacaoService, JwtTokenUtil jwtTokenUtil) {
        this.cientistasService = cientistasService;
        this.autenticacaoService = autenticacaoService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping(value = "/todosCientistas")
    public ResponseEntity<?> retornaTodosCientistas() {
        return ResponseEntity.ok(
                cientistasService.retornaTodosCientistas().stream().map(
                        cientistaModel -> CientistaUtil.preencherCiestistaDTO(new CientistaDTO(), cientistaModel)
                ).collect(Collectors.toList()));
    }

    @GetMapping(value = "/buscarCientista")
    public ResponseEntity<?> retornaCientistasPorNome(@RequestParam String nomeCientista) {
        return ResponseEntity.ok(
                cientistasService.retornaCientistaPorNome(nomeCientista).stream().map(
                        cientistaModel -> CientistaUtil.preencherCiestistaDTO(new CientistaDTO(), cientistaModel)
                ).collect(Collectors.toList()));
    }

    @PutMapping(value = "/editarPerfil/{cpfCientista}")
    public ResponseEntity<?> editarMeuPerfilCientista(@RequestBody @Valid CientistaDTO cientistaDTO,
                                                      @PathVariable(value = "cpfCientista") String cpfCientista,
                                                      @RequestHeader("Authorization") String token) {
        CientistaModel cientistaModel = autenticacaoService.loadUserByCpf(cpfCientista);


        if(cientistaModel.equals(autenticacaoService.loadUserByCpf(jwtTokenUtil.getSubjectFromToken(token))) &&
                cientistaDTO.getCpf().equals(cientistaModel.getCpf())) {
            CientistaUtil.preencherCientistaModel(cientistaModel, cientistaDTO);
            cientistasService.editarCientista(cientistaModel);
            return ResponseEntity.ok("Cientista " + cientistaModel.getNome() + " editado!");
        }

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Usuário não permitido a editar");
    }


}
