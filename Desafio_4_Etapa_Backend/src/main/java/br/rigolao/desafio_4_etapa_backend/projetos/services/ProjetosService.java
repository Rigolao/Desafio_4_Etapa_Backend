package br.rigolao.desafio_4_etapa_backend.projetos.services;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjetosService {


    public List<ProjetoModel> findAllProjects();

    @Transactional
    public void saveProjeto(ProjetoModel projeto);

    public List<ProjetoModel> retornarMeusProjetos(CientistaModel cientista);

    @Transactional
    public ProjetoModel retornaMeuProjeto(Integer id, CientistaModel cientista);

}
