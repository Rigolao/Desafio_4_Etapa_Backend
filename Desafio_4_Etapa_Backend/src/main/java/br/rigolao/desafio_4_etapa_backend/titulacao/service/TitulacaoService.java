package br.rigolao.desafio_4_etapa_backend.titulacao.service;

import br.rigolao.desafio_4_etapa_backend.models.TitulacaoModel;

import java.util.List;

public interface TitulacaoService {

    TitulacaoModel saveTitulacao(TitulacaoModel titulacaoModel);

    TitulacaoModel buscarTitulacao(String nome);

    List<TitulacaoModel> retornaTitulacoes();

}
