package br.rigolao.desafio_4_etapa_backend.telefone.services;

import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import br.rigolao.desafio_4_etapa_backend.telefone.repositories.TelefoneRepository;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TelefoneServiceImp extends LogUtil implements TelefoneService{

    private TelefoneRepository telefoneRepository;

    @Autowired
    public TelefoneServiceImp(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    @Override
    @Transactional
    public void saveTelefone(TelefoneModel telefone) {
        logInfo("Tentando salvar telefone");
        telefoneRepository.save(telefone);
    }

    @Override
    @Transactional
    public void deleteTelefone(TelefoneModel telefoneModel) {
        telefoneRepository.delete(telefoneModel);
        logInfo("Telefone deletado");
    }
}
