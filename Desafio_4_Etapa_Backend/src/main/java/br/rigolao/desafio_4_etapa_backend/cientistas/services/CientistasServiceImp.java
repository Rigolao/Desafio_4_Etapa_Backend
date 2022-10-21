package br.rigolao.desafio_4_etapa_backend.cientistas.services;

import br.rigolao.desafio_4_etapa_backend.cientistas.repositories.CientistasRepository;
import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CientistasServiceImp implements CientistasService{

    private CientistasRepository cientistasRepository;

    @Autowired
    public CientistasServiceImp(CientistasRepository cientistasRepository) {
        this.cientistasRepository = cientistasRepository;
    }

    @Override
    public List<CientistaModel> retornatodosCientistas() {
        return cientistasRepository.findAll();
    }

    @Override
    public List<CientistaModel> retornaCientistasPorAreaAtuacao(AreaAtuacaoModel areaAtuacao) {
        return cientistasRepository.findAllByAreaAtuacaoCientista(areaAtuacao).orElse(Collections.emptyList());
    }
}
