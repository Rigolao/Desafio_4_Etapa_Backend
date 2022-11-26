package br.rigolao.desafio_4_etapa_backend.areaAtuacao.services;

import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;

import java.util.List;

public interface AreaAtuacaoService {

    List<AreaAtuacaoModel> retornaTodasAreasAtuacao();

    AreaAtuacaoModel retornaAreaAtuacaoPorNome(String nome);

}
