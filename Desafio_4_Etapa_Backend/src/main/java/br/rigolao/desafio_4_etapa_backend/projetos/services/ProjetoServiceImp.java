package br.rigolao.desafio_4_etapa_backend.projetos.services;

import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import br.rigolao.desafio_4_etapa_backend.projetos.repositories.ProjetosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImp implements ProjetosService{

    private ProjetosRepository projetosRepository;

    @Autowired
    public ProjetoServiceImp(ProjetosRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    @Override
    public ProjetoModel findProjetoByCpf(String cpf) {
        return null;
    }

    public List<ProjetoModel> findAllProjects() {
        Optional<List<ProjetoModel>> listaProjetos = projetosRepository.findAllByPublico(true);
        return listaProjetos.orElse(Collections.emptyList());
    }

}
