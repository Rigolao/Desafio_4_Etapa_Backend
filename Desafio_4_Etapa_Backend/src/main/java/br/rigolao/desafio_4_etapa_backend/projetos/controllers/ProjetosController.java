package br.rigolao.desafio_4_etapa_backend.projetos.controllers;

import br.rigolao.desafio_4_etapa_backend.autenticacao.services.AutenticacaoServiceImp;
import br.rigolao.desafio_4_etapa_backend.config.security.utils.JwtTokenUtil;
import br.rigolao.desafio_4_etapa_backend.dtos.ProjetoDTO;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import br.rigolao.desafio_4_etapa_backend.projetos.services.ProjetoServiceImp;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/projetos")
public class ProjetosController extends LogUtil {

    private ProjetoServiceImp projetoService;

    private JwtTokenUtil jwtTokenUtil;

    private AutenticacaoServiceImp autenticacaoService;

    @Autowired
    public ProjetosController(
            ProjetoServiceImp projetoService, JwtTokenUtil jwtTokenUtil, AutenticacaoServiceImp autenticacaoService) {
        this.projetoService = projetoService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.autenticacaoService = autenticacaoService;
    }

    @GetMapping(value = "/todosProjetos")
    public ResponseEntity<?> retornaTodosProjetos() {
        List<ProjetoDTO> listaProjetos = new ArrayList<>();
        projetoService.findAllProjects().forEach(projetoModel -> {
            ProjetoDTO projetoTemp = new ProjetoDTO();
            BeanUtils.copyProperties(projetoModel, projetoTemp);
            BeanUtils.copyProperties(projetoModel.getCientista(), projetoTemp);
            listaProjetos.add(projetoTemp);
        });
        logInfo("Retornando todos os projetos");
        return ResponseEntity.ok(listaProjetos);
    }

    @PostMapping(value = "/cadastrarProjeto")
    public ResponseEntity<?> cadastrarProjeto(
            @RequestHeader("Authorization") String token, @RequestBody @Valid ProjetoDTO projeto){
        String cpf = jwtTokenUtil.getSubjectFromToken(token);
        CientistaModel cientista = autenticacaoService.loadUserByCpf(cpf);
        ProjetoModel projetoModel = new ProjetoModel();
        BeanUtils.copyProperties(projeto, projetoModel);
        projetoModel.setCientista(cientista);
        projetoService.saveProjeto(projetoModel);
        logInfo("Projeto do cientista " + cientista.getNome() + " com o nome "
            + projetoModel.getTitulo() + " cadastrado com sucesso!");
        return ResponseEntity.ok("Projeto cadastrado com sucesso!");
    }

    @GetMapping(value = "/meusProjetos")
    public ResponseEntity<?> meusProjetos(@RequestHeader("Authorization") String token) {
        String cpf = jwtTokenUtil.getSubjectFromToken(token);
        CientistaModel cientista = autenticacaoService.loadUserByCpf(cpf);
        List<ProjetoDTO> listaProjetos = new ArrayList<>();
        projetoService.retornarMeusProjetos(cientista).forEach(projetoModel -> {
            ProjetoDTO projetoTemp = new ProjetoDTO();
            BeanUtils.copyProperties(projetoModel, projetoTemp);
            BeanUtils.copyProperties(projetoModel.getCientista(), projetoTemp);
            listaProjetos.add(projetoTemp);
        });
        logInfo("Retornando todos os projetos do cientista " + cientista.getNome());
        return ResponseEntity.ok(listaProjetos);
    }

}
