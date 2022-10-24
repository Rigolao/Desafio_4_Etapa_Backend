package br.rigolao.desafio_4_etapa_backend.cientistas.controllers;

import br.rigolao.desafio_4_etapa_backend.cientistas.services.CientistasService;
import br.rigolao.desafio_4_etapa_backend.dtos.AreaAtuacaoDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.CientistaDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.RedesSociaisDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.TelefoneDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.formacao.FormacaoDTO;
import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;
import br.rigolao.desafio_4_etapa_backend.models.area_atuacao_cientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import br.rigolao.desafio_4_etapa_backend.utils.ObjectMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("cientistas")
public class CientistasController {

    private final CientistasService cientistasService;

    @Autowired
    public CientistasController(CientistasService cientistasService) {
        this.cientistasService = cientistasService;
    }

    @GetMapping(value = "/todosCientistas")
    public ResponseEntity<?> retornaTodosCientistas() {
        return ResponseEntity.ok(
                cientistasService.retornatodosCientistas().stream().map(cientistaModel -> {
                    CientistaDTO cientistaTemp = new CientistaDTO();
                    BeanUtils.copyProperties(cientistaModel, cientistaTemp);
                    cientistaTemp.setRedesSociais(_preencherRedesSociais(cientistaModel.getRedesSociais()));
                    cientistaTemp.setAreaAtuacaoCientista(_preencherAreaAtuacao(cientistaModel.getAreaAtuacaoCientista()));
                    cientistaTemp.setFormacoes(_preencherFormacoes(cientistaModel.getFormacoes()));
                    cientistaTemp.setTelefones(_preencherTelefones(cientistaModel.getTelefones()));
                    return cientistaTemp;
                }).collect(Collectors.toList()));
    }


    private List<RedesSociaisDTO> _preencherRedesSociais(List<RedesSociaisModel> redesSociaisModels) {
        return ObjectMapperUtil.mapAll(redesSociaisModels, RedesSociaisDTO.class);
    }

    private List<AreaAtuacaoDTO> _preencherAreaAtuacao(List<AreaAtuacaoCientistaModel> atuacaoCientistaModels) {
        return atuacaoCientistaModels.stream().map(
                model -> ObjectMapperUtil.map(
                        model.getAreaAtuacao(), AreaAtuacaoDTO.class)).collect(Collectors.toList());
    }

    private List<FormacaoDTO> _preencherFormacoes(List<FormacaoModel> listaFormacoes) {
        return listaFormacoes.stream().map(formacaoModel -> {
            FormacaoDTO temp = ObjectMapperUtil.map(formacaoModel, FormacaoDTO.class);
            temp.setNome(formacaoModel.getTitulacaoModel().getNome());
            return temp;
        }).collect(Collectors.toList());
    }

    private List<TelefoneDTO> _preencherTelefones(List<TelefoneModel> telefoneModels) {
        return telefoneModels.stream().map(telefoneModel ->
                ObjectMapperUtil.map(telefoneModel.getTelefone(), TelefoneDTO.class)
        ).collect(Collectors.toList());
    }

}
