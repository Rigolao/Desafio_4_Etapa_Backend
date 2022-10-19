package br.rigolao.desafio_4_etapa_backend.projetos.controllers;

import br.rigolao.desafio_4_etapa_backend.dtos.ProjetoDTO;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import br.rigolao.desafio_4_etapa_backend.projetos.services.ProjetoServiceImp;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/projetos")
public class ProjetosController extends LogUtil {

    private ProjetoServiceImp projetoService;

    @Autowired
    public ProjetosController(ProjetoServiceImp projetoService) {
        this.projetoService = projetoService;
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
        return ResponseEntity.ok(listaProjetos);
    }
}
