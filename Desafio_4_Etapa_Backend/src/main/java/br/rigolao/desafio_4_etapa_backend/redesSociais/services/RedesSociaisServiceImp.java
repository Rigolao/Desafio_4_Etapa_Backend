package br.rigolao.desafio_4_etapa_backend.redesSociais.services;

import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;
import br.rigolao.desafio_4_etapa_backend.redesSociais.repositories.RedesSociaisRepository;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RedesSociaisServiceImp extends LogUtil implements RedesSociaisService{

    private final RedesSociaisRepository redesSociaisRepository;

    public RedesSociaisServiceImp(RedesSociaisRepository redesSociaisRepository) {
        this.redesSociaisRepository = redesSociaisRepository;
    }

    @Override
    @Transactional
    public void saveRedeSocial(RedesSociaisModel redesSociaisModel) {
        logInfo("Salvando rede social");
        redesSociaisRepository.save(redesSociaisModel);
    }

    @Override
    @Transactional
    public void deleteRedeSocial(RedesSociaisModel redesSociaisModel) {
        redesSociaisRepository.delete(redesSociaisModel);
        logInfo("Rede social deletada");
    }
}
