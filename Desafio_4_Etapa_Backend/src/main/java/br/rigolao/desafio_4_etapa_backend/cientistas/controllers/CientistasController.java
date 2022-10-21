package br.rigolao.desafio_4_etapa_backend.cientistas.controllers;

import br.rigolao.desafio_4_etapa_backend.cientistas.services.CientistasServiceImp;
import br.rigolao.desafio_4_etapa_backend.dtos.AreaAtuacaoDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.CientistaDTO;
import br.rigolao.desafio_4_etapa_backend.models.area_atuacao_cientista.AreaAtuacaoCientistaModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("cientistas")
public class CientistasController {

    private CientistasServiceImp cientistasService;

    @Autowired
    public CientistasController(CientistasServiceImp cientistasService) {
        this.cientistasService = cientistasService;
    }

    @GetMapping(value = "/todosCientistas")
    public ResponseEntity<?> retornaTodosCientistas() {
        List<CientistaDTO> listaCientistas = new ArrayList<>();
        cientistasService.retornatodosCientistas().forEach(cientistaModel -> {
            CientistaDTO cientistaTemp = new CientistaDTO();
            BeanUtils.copyProperties(cientistaModel, cientistaTemp);
            _preencherAreaAtuacao(cientistaTemp, cientistaModel.getAreaAtuacaoCientista());
            listaCientistas.add(cientistaTemp);
        });
        return ResponseEntity.ok(listaCientistas);
    }

    private void _preencherAreaAtuacao(
            CientistaDTO cientistaDTO, List<AreaAtuacaoCientistaModel> atuacaoCientistaModels) {
        List<AreaAtuacaoDTO> listaAreaAtuacao = new ArrayList<>();
        atuacaoCientistaModels.forEach(areaAtuacaoCientistaModel -> {
            AreaAtuacaoDTO areaAtuacaoTemp = new AreaAtuacaoDTO();
            BeanUtils.copyProperties(areaAtuacaoCientistaModel.getAreaAtuacao(), areaAtuacaoTemp);
            listaAreaAtuacao.add(areaAtuacaoTemp);
            cientistaDTO.setAreaAtuacaoCientista(listaAreaAtuacao);
        });
    }
}
