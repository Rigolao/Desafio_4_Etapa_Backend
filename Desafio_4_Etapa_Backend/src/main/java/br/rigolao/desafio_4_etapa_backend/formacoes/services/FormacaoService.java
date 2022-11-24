package br.rigolao.desafio_4_etapa_backend.formacoes.services;

import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;

public interface FormacaoService {

    void saveFormacao(FormacaoModel formacaoModel);

    void deleteFormacoes(FormacaoModel formacaoModel);

}
