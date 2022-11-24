package br.rigolao.desafio_4_etapa_backend.cientistas.utils;

import br.rigolao.desafio_4_etapa_backend.dtos.*;
import br.rigolao.desafio_4_etapa_backend.dtos.formacao.FormacaoDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.formacao.FormacaoIdDTO;
import br.rigolao.desafio_4_etapa_backend.models.*;
import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaId;
import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoId;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneId;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import br.rigolao.desafio_4_etapa_backend.redesSociais.services.RedesSociaisService;
import br.rigolao.desafio_4_etapa_backend.utils.ObjectMapperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public final class CientistaUtil {

    public static CientistaModel preencherCientistaModel(CientistaModel cientistaModel, CientistaDTO cientistaDTO) {
        if(cientistaModel.getId() == null) {
            cientistaModel.setId(cientistaDTO.getId());
        }
        cientistaModel.setNome(cientistaDTO.getNome());
        cientistaModel.setCpf(cientistaDTO.getCpf());
        cientistaModel.setDataNascimento(cientistaDTO.getDataNascimento());
        cientistaModel.setEmail(cientistaDTO.getEmail());
        cientistaModel.setEmailAlternativo(cientistaDTO.getEmailAlternativo());
        cientistaModel.setLattes(cientistaDTO.getLattes());
        cientistaModel.setSnh(cientistaDTO.getSnh());


        if (cientistaDTO.getRedesSociais() != null) {
            cientistaModel.setRedesSociais(_preencherRedesSociaisModel(cientistaDTO.getRedesSociais(), cientistaModel));
        }

        if (cientistaDTO.getAreaAtuacaoCientista() != null) {
            cientistaModel.setAreaAtuacaoCientista(_preencherAreaAtuacaoCientistaModel(
                    _preencherAreaAtuacaoModels(cientistaDTO.getAreaAtuacaoCientista()), cientistaModel
            ));
        }

        if (cientistaDTO.getFormacoes() != null) {
            cientistaModel.setFormacoes(_preencherFormacoesModel(cientistaDTO.getFormacoes(), cientistaModel));
        }

        if (cientistaDTO.getTelefones() != null) {
            cientistaModel.setTelefones(_preencherTelefonesModel(cientistaDTO.getTelefones(), cientistaModel));
        }

        if (cientistaDTO.getProjetos() != null) {
            cientistaModel.setProjeto(_preencherProjetosModel(cientistaDTO.getProjetos(), cientistaModel));
        }

        return cientistaModel;
    }

    public static CientistaDTO preencherCiestistaDTO(CientistaDTO cientistaDTO, CientistaModel cientistaModel) {
        cientistaDTO.setId(cientistaModel.getId());
        cientistaDTO.setNome(cientistaModel.getNome());
        cientistaDTO.setCpf(cientistaModel.getCpf());
        cientistaDTO.setDataNascimento(cientistaModel.getDataNascimento());
        cientistaDTO.setEmail(cientistaModel.getEmail());
        cientistaDTO.setEmailAlternativo(cientistaModel.getEmailAlternativo());
        cientistaDTO.setLattes(cientistaModel.getLattes());
        cientistaDTO.setSnh(cientistaModel.getSnh());

        if (!cientistaModel.getRedesSociais().isEmpty()) {
            cientistaDTO.setRedesSociais(_preencherRedesSociaisDTO(cientistaModel.getRedesSociais()));
        }

        if (!cientistaModel.getAreaAtuacaoCientista().isEmpty()) {
            cientistaDTO.setAreaAtuacaoCientista(_preencherAreaAtuacaoDTO(cientistaModel.getAreaAtuacaoCientista()));
        }

        if (!cientistaModel.getFormacoes().isEmpty()) {
            cientistaDTO.setFormacoes(_preencherFormacoesDTO(cientistaModel.getFormacoes()));
        }

        if (!cientistaModel.getTelefones().isEmpty()) {
            cientistaDTO.setTelefones(_preencherTelefonesDTO(cientistaModel.getTelefones()));
        }

        if (!cientistaModel.getProjeto().isEmpty()) {
            cientistaDTO.setProjetos(_preencherProjetosDTO(cientistaModel.getProjeto()));
        }

        return cientistaDTO;
    }

    private static List<RedesSociaisModel> _preencherRedesSociaisModel(
            List<RedesSociaisDTO> redesSociaisDTOS, CientistaModel cientistaModel) {
        return redesSociaisDTOS.stream().map(
                        redesSociaisDTO -> new RedesSociaisModel(
                                redesSociaisDTO.getId(), cientistaModel, redesSociaisDTO.getEndereco(), redesSociaisDTO.getTipoRede()))
                .collect(Collectors.toList());
    }

    private static List<RedesSociaisDTO> _preencherRedesSociaisDTO(List<RedesSociaisModel> redesSociaisModels) {
        return ObjectMapperUtil.mapAll(redesSociaisModels, RedesSociaisDTO.class);
    }

    private static List<AreaAtuacaoCientistaModel> _preencherAreaAtuacaoCientistaModel(
            List<AreaAtuacaoModel> areaAtuacaoModels, CientistaModel cientistaModel) {
        return areaAtuacaoModels.stream().map(atuacaoModel -> {
            AreaAtuacaoCientistaId areaAtuacaoCientistaId = new AreaAtuacaoCientistaId(cientistaModel.getId(), atuacaoModel.getId());
            return new AreaAtuacaoCientistaModel(areaAtuacaoCientistaId, cientistaModel, atuacaoModel);
        }).collect(Collectors.toList());
    }

    private static List<AreaAtuacaoModel> _preencherAreaAtuacaoModels(List<AreaAtuacaoDTO> areaAtuacaoDTOS) {
        return ObjectMapperUtil.mapAll(areaAtuacaoDTOS, AreaAtuacaoModel.class);
    }

    private static List<AreaAtuacaoDTO> _preencherAreaAtuacaoDTO(List<AreaAtuacaoCientistaModel> atuacaoCientistaModels) {
        return atuacaoCientistaModels.stream().map(
                model -> ObjectMapperUtil.map(
                        model.getAreaAtuacao(), AreaAtuacaoDTO.class)).collect(Collectors.toList());
    }

    private static List<FormacaoModel> _preencherFormacoesModel(
            List<FormacaoDTO> formacaoDTOS, CientistaModel cientistaModel) {
        return formacaoDTOS.stream().map(formacaoDTO -> new FormacaoModel(
                new FormacaoId(), cientistaModel, new TitulacaoModel(null, formacaoDTO.getNome()),
                formacaoDTO.getDataInicio(), formacaoDTO.getDataTermino())).collect(Collectors.toList());
    }

    private static List<FormacaoDTO> _preencherFormacoesDTO(List<FormacaoModel> listaFormacoes) {
        return listaFormacoes.stream().map(formacaoModel ->
                new FormacaoDTO(ObjectMapperUtil.map(formacaoModel.getId(), FormacaoIdDTO.class),
                        formacaoModel.getDataInicio(), formacaoModel.getDataTermino(),
                        formacaoModel.getTitulacaoModel().getNome())).collect(Collectors.toList());
    }

    private static List<TelefoneModel> _preencherTelefonesModel(List<TelefoneDTO> telefoneDTOS, CientistaModel cientistaModel) {
        return telefoneDTOS.stream().map(telefoneDTO -> {
            TelefoneId telefoneId = new TelefoneId(telefoneDTO.getDdd(), telefoneDTO.getIdCientista(), telefoneDTO.getNumero());
            return new TelefoneModel(telefoneId, cientistaModel);
        }).collect(Collectors.toList());
    }

    private static List<TelefoneDTO> _preencherTelefonesDTO(List<TelefoneModel> telefoneModels) {
        return ObjectMapperUtil.mapAll(
                telefoneModels.stream().map(TelefoneModel::getTelefone)
                        .collect(Collectors.toList()), TelefoneDTO.class);
    }

    private static List<ProjetoModel> _preencherProjetosModel(List<ProjetoDTO> projetoDTOS, CientistaModel cientistaModel) {
        return projetoDTOS.stream().map(projetoDTO -> {
            ProjetoModel temp = ObjectMapperUtil.map(projetoDTO, ProjetoModel.class);
            temp.setCientista(cientistaModel);
            return temp;
        }).collect(Collectors.toList());
    }

    private static List<ProjetoDTO> _preencherProjetosDTO(List<ProjetoModel> projetoModels) {
        return projetoModels.stream().map(projetoModel -> {
                    ProjetoDTO projetoTemp = ObjectMapperUtil.map(projetoModel, ProjetoDTO.class);
                    BeanUtils.copyProperties(projetoModel.getCientista(), projetoTemp);
                    return projetoTemp;
                }
        ).collect(Collectors.toList());
    }

}
