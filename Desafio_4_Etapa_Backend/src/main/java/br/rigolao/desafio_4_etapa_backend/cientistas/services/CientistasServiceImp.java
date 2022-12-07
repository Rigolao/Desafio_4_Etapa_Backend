package br.rigolao.desafio_4_etapa_backend.cientistas.services;

import br.rigolao.desafio_4_etapa_backend.areaAtuacao.services.AreaAtuacaoService;
import br.rigolao.desafio_4_etapa_backend.areaAtuacaoCientista.services.AreaAtuacaoCientistaService;
import br.rigolao.desafio_4_etapa_backend.cientistas.repositories.CientistasRepository;
import br.rigolao.desafio_4_etapa_backend.exceptions.DataInvalidaException;
import br.rigolao.desafio_4_etapa_backend.exceptions.EmailCadastradoException;
import br.rigolao.desafio_4_etapa_backend.exceptions.LattesCadastradoException;
import br.rigolao.desafio_4_etapa_backend.exceptions.UsuarioNaoEncontradoException;
import br.rigolao.desafio_4_etapa_backend.formacoes.services.FormacaoService;
import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.redesSociais.services.RedesSociaisService;
import br.rigolao.desafio_4_etapa_backend.telefone.services.TelefoneService;
import br.rigolao.desafio_4_etapa_backend.titulacao.service.TitulacaoService;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CientistasServiceImp extends LogUtil implements CientistasService{

    private final CientistasRepository cientistasRepository;

    private final TelefoneService telefoneService;

    private final  AreaAtuacaoCientistaService areaAtuacaoCientistaService;

    private final  RedesSociaisService redesSociaisService;

    private final  FormacaoService formacaoService;

    private final AreaAtuacaoService areaAtuacaoService;

    private final TitulacaoService titulacaoService;

    @Autowired
    public CientistasServiceImp(CientistasRepository cientistasRepository, TelefoneService telefoneService, AreaAtuacaoCientistaService areaAtuacaoCientistaService, RedesSociaisService redesSociaisService, FormacaoService formacaoService, AreaAtuacaoService areaAtuacaoService, TitulacaoService titulacaoService) {
        this.cientistasRepository = cientistasRepository;
        this.telefoneService = telefoneService;
        this.areaAtuacaoCientistaService = areaAtuacaoCientistaService;
        this.redesSociaisService = redesSociaisService;
        this.formacaoService = formacaoService;
        this.areaAtuacaoService = areaAtuacaoService;
        this.titulacaoService = titulacaoService;
    }

    @Override
    public List<CientistaModel> retornaTodosCientistas() {
        logInfo("Retornando todos os cientistas");
        return cientistasRepository.findAll();
    }

    @Override
    public List<CientistaModel> retornaCientistaPorNome(String nomeCientista) {
        logInfo("Retornando cientista pelo nome: " + nomeCientista);
        return cientistasRepository.findAllByNomeStartingWithIgnoreCase(nomeCientista).orElse(Collections.emptyList());
    }

    @Override
    public CientistaModel retornaCientistaPorCpf(String cpf) {
        logInfo("Retornando cientista pelo cpf: " + cpf);
        return cientistasRepository.findByCpf(cpf).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @Override
    @Transactional
    public void editarCientista(CientistaModel cientistaModel) {
        CientistaModel cientistaModelTemp = cientistasRepository.findCientistaModelById(cientistaModel.getId())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if(cientistasRepository.existsByEmail(cientistaModel.getEmail()) &&
                !cientistasRepository.existsByEmailAndId(cientistaModel.getEmail(), cientistaModel.getId())) {
            throw new EmailCadastradoException();
        }

        if(cientistasRepository.existsByLattes(cientistaModel.getLattes()) &&
                !cientistasRepository.existsByLattesAndId(cientistaModel.getLattes(), cientistaModel.getId())) {
            throw new LattesCadastradoException();
        }

        if(cientistaModel.getDataNascimento().after(new Date()) ||
                DateUtils.isSameDay(cientistaModel.getDataNascimento(), new Date())) {
            throw new DataInvalidaException();
        }

        if(cientistaModel.getTelefones() != null) {
            _editarTelefone(cientistaModel, cientistaModelTemp);
        }

        if(cientistaModel.getRedesSociais() != null) {
            _editarRedesSociais(cientistaModel, cientistaModelTemp);
        }

        if(cientistaModel.getAreaAtuacaoCientista() != null) {
            _editarAreaAtuacaoCientista(cientistaModel, cientistaModelTemp);
        }

        if(cientistaModel.getFormacoes() != null) {
            _editarFormacoes(cientistaModel, cientistaModelTemp);
        }

        cientistasRepository.save(cientistaModel);
        logInfo("Cientista editado!");
    }

    private void _editarTelefone(CientistaModel cientistaNew, CientistaModel cientistaOld) {
        cientistaOld.getTelefones().forEach(telefoneService::deleteTelefone);

        cientistaNew.getTelefones().forEach(telefoneModel -> {
            telefoneModel.setCientista(cientistaOld);
            telefoneService.saveTelefone(telefoneModel);
        });
    }

    private void _editarRedesSociais(CientistaModel cientistaNew, CientistaModel cientistaOld) {
        cientistaOld.getRedesSociais().forEach(redesSociaisService::deleteRedeSocial);

        cientistaNew.getRedesSociais().forEach(redesSociaisModel -> {
            redesSociaisModel.setCientista(cientistaOld);
            redesSociaisService.saveRedeSocial(redesSociaisModel);
        });
    }

    private void _editarAreaAtuacaoCientista(CientistaModel cientistaNew, CientistaModel cientistaOld) {
        cientistaOld.getAreaAtuacaoCientista().forEach(areaAtuacaoCientistaService::deletarAreaAtuacaoCientista);

        cientistaNew.getAreaAtuacaoCientista().forEach(areaAtuacaoCientistaModel -> {
            areaAtuacaoCientistaModel.setCientista(cientistaOld);
            areaAtuacaoCientistaModel.setAreaAtuacao(
                    areaAtuacaoService.retornaAreaAtuacaoPorNome(
                            areaAtuacaoCientistaModel.getAreaAtuacao().getNome()));
            areaAtuacaoCientistaService.saveAreaAtuacaoCientista(areaAtuacaoCientistaModel);
        });
    }

    private void _editarFormacoes(CientistaModel cientistaNew, CientistaModel cientistaOld) {
        cientistaOld.getFormacoes().forEach(formacaoService::deleteFormacoes);

        cientistaNew.getFormacoes().forEach(formacaoModel -> {
            formacaoModel.setTitulacaoModel(titulacaoService.buscarTitulacao(formacaoModel.getTitulacaoModel().getNome()));
            formacaoModel.setCientista(cientistaOld);
            formacaoService.saveFormacao(formacaoModel);
        });
    }
}
