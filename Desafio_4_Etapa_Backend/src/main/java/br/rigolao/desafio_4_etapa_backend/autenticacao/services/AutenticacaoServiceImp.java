package br.rigolao.desafio_4_etapa_backend.autenticacao.services;

import br.rigolao.desafio_4_etapa_backend.autenticacao.repositories.AutenticacaoRepository;
import br.rigolao.desafio_4_etapa_backend.exceptions.*;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AutenticacaoServiceImp extends LogUtil implements UserDetailsService, AutenticacaoService {

    private final AutenticacaoRepository autenticacaoRepository;

    @Autowired
    public AutenticacaoServiceImp(AutenticacaoRepository autenticacaoRepository) {
        this.autenticacaoRepository = autenticacaoRepository;
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

}
