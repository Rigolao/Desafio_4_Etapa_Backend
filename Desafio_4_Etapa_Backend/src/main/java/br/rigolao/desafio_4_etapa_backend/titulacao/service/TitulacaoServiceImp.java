package br.rigolao.desafio_4_etapa_backend.titulacao.service;

import br.rigolao.desafio_4_etapa_backend.models.TitulacaoModel;
import br.rigolao.desafio_4_etapa_backend.titulacao.repositories.TitulacaoRepository;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TitulacaoServiceImp extends LogUtil implements TitulacaoService{

    private final TitulacaoRepository titulacaoRepository;

    @Autowired
    public TitulacaoServiceImp(TitulacaoRepository titulacaoRepository) {
        this.titulacaoRepository = titulacaoRepository;
    }

    @Override
    @Transactional
    public TitulacaoModel saveTitulacao(TitulacaoModel titulacaoModel) {
        logInfo("Titulação " + titulacaoModel.getNome() + " salva!");
        return titulacaoRepository.save(titulacaoModel);
    }

    @Override
    public TitulacaoModel buscarTitulacao(String nome) {
        return titulacaoRepository.findByNome(nome);
    }

    @Override
    public List<TitulacaoModel> retornaTitulacoes() {
        return titulacaoRepository.findAll();
    }
}
