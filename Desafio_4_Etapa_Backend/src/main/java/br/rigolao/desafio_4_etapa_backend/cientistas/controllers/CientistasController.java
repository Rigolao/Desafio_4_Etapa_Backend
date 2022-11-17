package br.rigolao.desafio_4_etapa_backend.cientistas.controllers;

import br.rigolao.desafio_4_etapa_backend.cientistas.services.CientistasService;
import br.rigolao.desafio_4_etapa_backend.cientistas.utils.CientistaUtil;
import br.rigolao.desafio_4_etapa_backend.dtos.*;
import br.rigolao.desafio_4_etapa_backend.dtos.formacao.FormacaoDTO;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;
import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import br.rigolao.desafio_4_etapa_backend.utils.ObjectMapperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @PutMapping
//    public ResponseEntity<?> editarMeuPerfilCientista(@RequestBody CientistaDTO cientistaDTO) {
//        return ResponseEntity.ok();
//    }

//    private CientistaDTO _preencheCientistaDTO(CientistaModel cientistaModel) {
//        CientistaDTO cientistaTemp = new CientistaDTO();
//        BeanUtils.copyProperties(cientistaModel, cientistaTemp);
//        cientistaTemp.setRedesSociais(_preencherRedesSociais(cientistaModel.getRedesSociais()));
//        cientistaTemp.setAreaAtuacaoCientista(_preencherAreaAtuacao(cientistaModel.getAreaAtuacaoCientista()));
//        cientistaTemp.setFormacoes(_preencherFormacoes(cientistaModel.getFormacoes()));
//        cientistaTemp.setTelefones(_preencherTelefones(cientistaModel.getTelefones()));
//        cientistaTemp.setProjetos(_preencherProjetos(cientistaModel.getProjeto()));
//        return cientistaTemp;
//    }
//
//
//    private List<RedesSociaisDTO> _preencherRedesSociais(List<RedesSociaisModel> redesSociaisModels) {
//        return ObjectMapperUtil.mapAll(redesSociaisModels, RedesSociaisDTO.class);
//    }
//
//    private List<AreaAtuacaoDTO> _preencherAreaAtuacao(List<AreaAtuacaoCientistaModel> atuacaoCientistaModels) {
//        return atuacaoCientistaModels.stream().map(
//                model -> ObjectMapperUtil.map(
//                        model.getAreaAtuacao(), AreaAtuacaoDTO.class)).collect(Collectors.toList());
//    }
//
//    private List<FormacaoDTO> _preencherFormacoes(List<FormacaoModel> listaFormacoes) {
//        return listaFormacoes.stream().map(formacaoModel -> {
//            FormacaoDTO temp = ObjectMapperUtil.map(formacaoModel, FormacaoDTO.class);
//            temp.setNome(formacaoModel.getTitulacaoModel().getNome());
//            return temp;
//        }).collect(Collectors.toList());
//    }
//
//    private List<TelefoneDTO> _preencherTelefones(List<TelefoneModel> telefoneModels) {
//        return ObjectMapperUtil.mapAll(
//                telefoneModels.stream().map(TelefoneModel::getTelefone)
//                        .collect(Collectors.toList()), TelefoneDTO.class);
//    }
//
//    private List<ProjetoDTO> _preencherProjetos(List<ProjetoModel> projetoModels) {
//        return projetoModels.stream().map(projetoModel -> {
//                    ProjetoDTO projetoTemp = ObjectMapperUtil.map(projetoModel, ProjetoDTO.class);
//                    BeanUtils.copyProperties(projetoModel.getCientista(), projetoTemp);
//                    return projetoTemp;
//                }
//        ).collect(Collectors.toList());
//    }

}
