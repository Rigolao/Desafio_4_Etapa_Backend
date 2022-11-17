package br.rigolao.desafio_4_etapa_backend.redesSociais.services;

import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;
import br.rigolao.desafio_4_etapa_backend.redesSociais.repositories.RedesSociaisRepository;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.stereotype.Service;

@Service
public class RedesSociaisServiceImp extends LogUtil implements RedesSociaisService{

    private final RedesSociaisRepository redesSociaisRepository;

    public RedesSociaisServiceImp(RedesSociaisRepository redesSociaisRepository) {
        this.redesSociaisRepository = redesSociaisRepository;
    }

    @Override
    public void saveRedeSocial(RedesSociaisModel redesSociaisModel) {
        logInfo("Salvando rede social");
        redesSociaisRepository.save(redesSociaisModel);
    }
}
