package br.rigolao.desafio_4_etapa_backend.formacoes.services;

import br.rigolao.desafio_4_etapa_backend.formacoes.repositories.FormacaoRepository;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FormacaoServiceImp extends LogUtil implements FormacaoService{

    private final FormacaoRepository formacaoRepository;

    @Autowired
    public FormacaoServiceImp(FormacaoRepository formacaoRepository) {
        this.formacaoRepository = formacaoRepository;
    }

    @Override
    @Transactional
    public void saveFormacao(FormacaoModel formacaoModel) {
        formacaoRepository.save(formacaoModel);
        logInfo("Formação " + formacaoModel.getTitulacaoModel().getNome() + " salva!");
    }
}
