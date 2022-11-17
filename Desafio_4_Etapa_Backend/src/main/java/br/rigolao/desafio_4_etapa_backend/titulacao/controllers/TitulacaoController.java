package br.rigolao.desafio_4_etapa_backend.titulacao.controllers;

import br.rigolao.desafio_4_etapa_backend.dtos.TitulacaoDTO;
import br.rigolao.desafio_4_etapa_backend.titulacao.service.TitulacaoService;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import br.rigolao.desafio_4_etapa_backend.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/titulacao")
public class TitulacaoController extends LogUtil {

    private final TitulacaoService titulacaoService;

    @Autowired
    public TitulacaoController(TitulacaoService titulacaoService) {
        this.titulacaoService = titulacaoService;
    }

    @GetMapping(value = "/retornaTitulacoes")
    public ResponseEntity<?> retornaTodasTitulacoes(){
        return ResponseEntity.ok(
                ObjectMapperUtil.mapAll(titulacaoService.retornaTitulacoes(), TitulacaoDTO.class));
    }

}
