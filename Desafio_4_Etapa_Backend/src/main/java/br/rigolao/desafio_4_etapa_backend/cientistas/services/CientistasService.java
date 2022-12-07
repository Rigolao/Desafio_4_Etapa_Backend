package br.rigolao.desafio_4_etapa_backend.cientistas.services;

import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;

import java.util.List;

public interface CientistasService {

    List<CientistaModel> retornaTodosCientistas();

    List<CientistaModel> retornaCientistaPorNome(String nomeCientista);

    CientistaModel retornaCientistaPorCpf(String cpf);

    void editarCientista(CientistaModel cientistaModel);

}
