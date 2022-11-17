package br.rigolao.desafio_4_etapa_backend.cientistas.services;

import br.rigolao.desafio_4_etapa_backend.cientistas.repositories.CientistasRepository;
import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CientistasServiceImp extends LogUtil implements CientistasService{

    private CientistasRepository cientistasRepository;

    @Autowired
    public CientistasServiceImp(CientistasRepository cientistasRepository) {
        this.cientistasRepository = cientistasRepository;
    }

    @Override
    public List<CientistaModel> retornaTodosCientistas() {
        logInfo("Retornando todos os cientistas");
        return cientistasRepository.findAll();
    }

    @Override
    public List<CientistaModel> retornaCientistaPorNome(String nomeCientista) {
        logInfo("Retornando cientista pelo nome: " + nomeCientista);
        return cientistasRepository.findAllByNomeStartingWithIgnoreCase(nomeCientista).orElse(Collections.emptyList());
    }

    @Override
    public List<CientistaModel> retornaCientistasPorAreaAtuacao(AreaAtuacaoModel areaAtuacao) {
        logInfo("Retornando todos os cientistas pela area de atuação: " + areaAtuacao.getNome());
        return cientistasRepository.findAllByAreaAtuacaoCientista(areaAtuacao).orElse(Collections.emptyList());
    }

    @Override
    public void editarCientista(CientistaModel cientistaModel) {

    }
}
