package br.rigolao.desafio_4_etapa_backend.areaAtuacao.controllers;

import br.rigolao.desafio_4_etapa_backend.areaAtuacao.services.AreaAtuacaoService;
import br.rigolao.desafio_4_etapa_backend.dtos.AreaAtuacaoDTO;
import br.rigolao.desafio_4_etapa_backend.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/areaAtuacao")
public class AreaAtuacaoController {

    private final AreaAtuacaoService areaAtuacaoService;

    @Autowired
    public AreaAtuacaoController(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    @GetMapping("/todasAreasAtuacao")
    public ResponseEntity<?> retornaTodasAreasAtuacao() {
        return ResponseEntity.ok(ObjectMapperUtil.mapAll(
                areaAtuacaoService.retornaTodasAreasAtuacao(), AreaAtuacaoDTO.class));
    }
}
