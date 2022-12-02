package br.rigolao.desafio_4_etapa_backend.projetos.controllers;

import br.rigolao.desafio_4_etapa_backend.autenticacao.services.AutenticacaoServiceImp;
import br.rigolao.desafio_4_etapa_backend.config.security.utils.JwtTokenUtil;
import br.rigolao.desafio_4_etapa_backend.dtos.ProjetoDTO;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import br.rigolao.desafio_4_etapa_backend.projetos.services.ProjetoServiceImp;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import br.rigolao.desafio_4_etapa_backend.utils.ObjectMapperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/projetos")
public class ProjetosController extends LogUtil {

    private final ProjetoServiceImp projetoService;

    private final JwtTokenUtil jwtTokenUtil;

    private final AutenticacaoServiceImp autenticacaoService;

    @Autowired
    public ProjetosController(
            ProjetoServiceImp projetoService, JwtTokenUtil jwtTokenUtil, AutenticacaoServiceImp autenticacaoService) {
        this.projetoService = projetoService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.autenticacaoService = autenticacaoService;
    }

    @GetMapping(value = "/todosProjetos")
    public ResponseEntity<?> retornaTodosProjetos() {
        logInfo("Retornando todos os projetos");
        return ResponseEntity.ok(_carregarProjetos(projetoService.findAllProjects()));
    }

    @PostMapping(value = "/cadastrarProjeto")
    public ResponseEntity<?> cadastrarProjeto(
            @RequestHeader("Authorization") String token, @RequestBody @Valid ProjetoDTO projeto) {
        CientistaModel cientista = _carregarCientista(token);
        ProjetoModel projetoModel = ObjectMapperUtil.map(projeto, ProjetoModel.class);
        projetoModel.setCientista(cientista);
        projetoService.saveProjeto(projetoModel);
        logInfo("Projeto do cientista " + cientista.getNome() + " com o nome "
                + projetoModel.getTitulo() + " cadastrado com sucesso!");
        return ResponseEntity.ok("Projeto cadastrado com sucesso!");
    }

    @GetMapping(value = "/meusProjetos")
    public ResponseEntity<?> meusProjetos(@RequestHeader("Authorization") String token) {
        CientistaModel cientista = _carregarCientista(token);
        logInfo("Retornando todos os projetos do cientista " + cientista.getNome());
        return ResponseEntity.ok(_carregarProjetos(projetoService.retornarMeusProjetos(cientista)));
    }

    @PutMapping(value = "/editarProjeto/{id}")
    public ResponseEntity<?> editarProjeto(
            @PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String token,
            @RequestBody @Valid ProjetoDTO projeto) {
        CientistaModel cientista = _carregarCientista(token);
        ProjetoModel meuProjeto = projetoService.retornaMeuProjeto(id, cientista);
        BeanUtils.copyProperties(projeto, meuProjeto, "id");
        projetoService.saveProjeto(meuProjeto);
        logInfo("Projeto de nome: " + meuProjeto.getTitulo() + " alterado!");
        return ResponseEntity.ok("Projeto editado com sucesso!");
    }

    @DeleteMapping(value = "/deletarProjeto/{id}")
    public ResponseEntity<?> deletarProjeto(
            @PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String token) {
        CientistaModel cientista = _carregarCientista(token);
        ProjetoModel projeto = projetoService.retornaMeuProjeto(id, cientista);
        projetoService.deletarProjeto(projeto);
        logInfo("Projeto deletado com sucesso!");
        return ResponseEntity.ok("Projeto deletado com sucesso!");
    }

    private List<ProjetoDTO> _carregarProjetos(List<ProjetoModel> projetoModels) {
        return projetoModels.stream().map(projetoModel -> {
            ProjetoDTO projetoDTO = ObjectMapperUtil.map(projetoModel, ProjetoDTO.class);
            BeanUtils.copyProperties(projetoModel.getCientista(), projetoDTO);
            projetoDTO.setIdProjeto(projetoModel.getId());
            return projetoDTO;
        }).collect(Collectors.toList());
    }

    private CientistaModel _carregarCientista(String token) {
        String cpf = jwtTokenUtil.getSubjectFromToken(token);
        return autenticacaoService.loadUserByCpf(cpf);
    }

}
