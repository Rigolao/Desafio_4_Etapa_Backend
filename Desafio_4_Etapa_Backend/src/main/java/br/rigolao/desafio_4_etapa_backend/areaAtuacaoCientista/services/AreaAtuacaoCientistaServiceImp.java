package br.rigolao.desafio_4_etapa_backend.areaAtuacaoCientista.services;

import br.rigolao.desafio_4_etapa_backend.areaAtuacaoCientista.repositories.AreaAtucaoCientistaRepository;
import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AreaAtuacaoCientistaServiceImp extends LogUtil implements AreaAtuacaoCientistaService{

    private final AreaAtucaoCientistaRepository areaAtucaoCientistaRepository;

    @Autowired
    public AreaAtuacaoCientistaServiceImp(AreaAtucaoCientistaRepository areaAtucaoCientistaRepository) {
        this.areaAtucaoCientistaRepository = areaAtucaoCientistaRepository;
    }

    @Override
    @Transactional
    public void saveAreaAtuacaoCientista(AreaAtuacaoCientistaModel areaAtuacaoCientistaModel) {
        logInfo("Salvando area de atuacção do cientista");
        areaAtucaoCientistaRepository.save(areaAtuacaoCientistaModel);
    }
}
