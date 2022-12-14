package br.rigolao.desafio_4_etapa_backend.projetos.services;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjetosService {

    List<ProjetoModel> findAllProjects();

    void saveProjeto(ProjetoModel projeto);

    List<ProjetoModel> retornarMeusProjetos(CientistaModel cientista);

    ProjetoModel retornaMeuProjeto(Integer id, CientistaModel cientista);

    void deletarProjeto(ProjetoModel projeto);

}
