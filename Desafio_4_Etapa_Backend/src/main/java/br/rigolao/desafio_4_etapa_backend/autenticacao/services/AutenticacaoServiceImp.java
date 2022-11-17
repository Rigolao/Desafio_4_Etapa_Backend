package br.rigolao.desafio_4_etapa_backend.autenticacao.services;

import br.rigolao.desafio_4_etapa_backend.areaAtuacao.services.AreaAtuacaoService;
import br.rigolao.desafio_4_etapa_backend.areaAtuacaoCientista.services.AreaAtuacaoCientistaService;
import br.rigolao.desafio_4_etapa_backend.autenticacao.repositories.AutenticacaoRepository;
import br.rigolao.desafio_4_etapa_backend.exceptions.*;
import br.rigolao.desafio_4_etapa_backend.formacoes.services.FormacaoService;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;
import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoId;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import br.rigolao.desafio_4_etapa_backend.redesSociais.services.RedesSociaisService;
import br.rigolao.desafio_4_etapa_backend.telefone.services.TelefoneService;
import br.rigolao.desafio_4_etapa_backend.titulacao.service.TitulacaoService;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AutenticacaoServiceImp extends LogUtil implements UserDetailsService, AutenticacaoService {

    private final AutenticacaoRepository autenticacaoRepository;

    private final TelefoneService telefoneService;

    private final RedesSociaisService redesSociaisService;

    private final AreaAtuacaoCientistaService areaAtuacaoCientistaService;

    private final AreaAtuacaoService areaAtuacaoService;

    private final FormacaoService formacaoService;

    private final TitulacaoService titulacaoService;

    @Autowired
    public AutenticacaoServiceImp(AutenticacaoRepository autenticacaoRepository, TelefoneService telefoneService, RedesSociaisService redesSociaisService, AreaAtuacaoCientistaService areaAtuacaoCientistaService, AreaAtuacaoService areaAtuacaoService, FormacaoService formacaoService, TitulacaoService titulacaoService) {
        this.autenticacaoRepository = autenticacaoRepository;
        this.telefoneService = telefoneService;
        this.redesSociaisService = redesSociaisService;
        this.areaAtuacaoCientistaService = areaAtuacaoCientistaService;
        this.areaAtuacaoService = areaAtuacaoService;
        this.formacaoService = formacaoService;
        this.titulacaoService = titulacaoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    @Transactional
    public CientistaModel loadUserByCpf(String cpf) throws UsuarioNaoEncontradoException {
        CientistaModel cientista = autenticacaoRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsuarioNaoEncontradoException());
        logInfo("Cientista encontrado. Nome: " + cientista.getNome());
        return new CientistaModel(cientista.getId(), cientista.getNome(), cientista.getCpf(),
                cientista.getDataNascimento(), cientista.getEmail(), cientista.getEmailAlternativo(),
                cientista.getLattes(), cientista.getSnh(), cientista.getRedesSociais(), cientista.getFormacoes(),
                cientista.getTelefones(), cientista.getAreaAtuacaoCientista(), cientista.getProjeto());
    }

    @Override
    @Transactional
    public void cadastrarCientista(CientistaModel cientista) {
        try{
            logInfo("Cientista tentando cadastro.");
            autenticacaoRepository.save(cientista);

            if (cientista.getTelefones() != null) {
                _salvarTelefones(cientista.getTelefones());
                logInfo("Cadastro de telefone(s) do cientista: " + cientista.getNome());
            }

            if (cientista.getRedesSociais() != null) {
                _salvarRedesSociais(cientista.getRedesSociais());
                logInfo("Cadastro de rede(s) social(is) do cientista: " + cientista.getNome());
            }

            if(cientista.getAreaAtuacaoCientista() != null) {
                _salvarAreasAtuacao(cientista.getAreaAtuacaoCientista());
                logInfo("Cadastro de área(s) de atuação do cientista: " + cientista.getNome());
            }

            if(cientista.getFormacoes() != null) {
                _salvarFormacoes(cientista.getFormacoes());
                logInfo("Cadastro de formação(ões) do cientista: " + cientista.getNome());
            }
        } catch (Exception e) {
            logInfo("Erro no cadastro do cientista! Fazendo rollback");
            throw new ErroCadastroCientistaException();
        }
    }

    @Override
    @Transactional
    public CientistaModel saveCientista(CientistaModel cientista) {
        if (autenticacaoRepository.existsByCpf(cientista.getCpf())) {
            throw new CpfJaCadastradoException();
        }
        if(autenticacaoRepository.existsByEmail(cientista.getEmail())){
            throw new EmailCadastradoException();
        }
        if(autenticacaoRepository.existsByLattes(cientista.getLattes())){
            throw new LattesCadastradoException();
        }
        logInfo("Cientista salvo no banco de dados");
        return autenticacaoRepository.save(cientista);
    }

    @Override
    @Transactional
    public CientistaModel updateCientista(CientistaModel cientistaModel) {
        if(!autenticacaoRepository.existsByCpf(cientistaModel.getCpf())){
            throw new UsuarioNaoEncontradoException();
        }
        logInfo("Atualizando cadastro do cientista: " + cientistaModel.getNome());
        return autenticacaoRepository.save(cientistaModel);
    }

    @Override
    @Transactional
    public void deleteCientista(CientistaModel cientistaModel) {
        autenticacaoRepository.delete(cientistaModel);
    }

    private void _salvarTelefones(List<TelefoneModel> lista) {
        lista.forEach(telefoneService::saveTelefone);
    }

    private void _salvarRedesSociais(List<RedesSociaisModel> lista) {
        lista.forEach(redesSociaisService::saveRedeSocial);
    }

    private void _salvarAreasAtuacao(List<AreaAtuacaoCientistaModel> lista) {
        lista.forEach(areaAtuacaoCientistaModel -> {
            areaAtuacaoCientistaModel.setAreaAtuacao(
                    areaAtuacaoService.retornaAreaAtuacaoPorNome(areaAtuacaoCientistaModel.getAreaAtuacao().getNome()));
            areaAtuacaoCientistaService.saveAreaAtuacaoCientista(areaAtuacaoCientistaModel);
        });
    }

    private void _salvarFormacoes(List<FormacaoModel> lista) {
        lista.forEach(formacaoModel -> {
            formacaoModel.setTitulacaoModel(titulacaoService.buscarTitulacao(formacaoModel.getTitulacaoModel().getNome()));
            formacaoModel.setId(new FormacaoId(
                    formacaoModel.getCientista().getId(), formacaoModel.getTitulacaoModel().getId()));
            formacaoService.saveFormacao(formacaoModel);
        });
    }
}
