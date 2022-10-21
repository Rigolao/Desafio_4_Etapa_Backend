package br.rigolao.desafio_4_etapa_backend.cientistas.services;

import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;

import java.util.List;

public interface CientistasService {

    public List<CientistaModel> retornatodosCientistas();

    public List<CientistaModel> retornaCientistasPorAreaAtuacao(AreaAtuacaoModel areaAtuacao);

}
