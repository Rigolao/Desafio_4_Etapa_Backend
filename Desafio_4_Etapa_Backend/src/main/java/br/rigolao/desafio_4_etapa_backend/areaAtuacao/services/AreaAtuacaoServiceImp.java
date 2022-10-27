package br.rigolao.desafio_4_etapa_backend.areaAtuacao.services;

import br.rigolao.desafio_4_etapa_backend.areaAtuacao.repositories.AreaAtuacaoRepository;
import br.rigolao.desafio_4_etapa_backend.exceptions.AreaAtuacaoNaoEncontradaException;
import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaAtuacaoServiceImp implements AreaAtuacaoService{

    private AreaAtuacaoRepository areaAtuacaoRepository;

    @Autowired
    public AreaAtuacaoServiceImp(AreaAtuacaoRepository areaAtuacaoRepository) {
        this.areaAtuacaoRepository = areaAtuacaoRepository;
    }

    @Override
    public List<AreaAtuacaoModel> retornaTodasAreasAtuacao() {
       return areaAtuacaoRepository.findAll();
    }

    @Override
    public AreaAtuacaoModel retornaAreaAtuacaoPorNome(String nome) {
        return areaAtuacaoRepository.findByNome(nome).orElseThrow(AreaAtuacaoNaoEncontradaException::new);
    }
}
