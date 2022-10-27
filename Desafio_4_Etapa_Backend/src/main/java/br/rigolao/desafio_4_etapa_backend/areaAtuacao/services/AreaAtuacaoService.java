package br.rigolao.desafio_4_etapa_backend.areaAtuacao.services;

import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AreaAtuacaoService {

    public List<AreaAtuacaoModel> retornaTodasAreasAtuacao();

    public AreaAtuacaoModel retornaAreaAtuacaoPorNome(String nome);

}
