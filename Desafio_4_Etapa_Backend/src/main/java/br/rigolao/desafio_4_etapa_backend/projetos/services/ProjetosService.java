package br.rigolao.desafio_4_etapa_backend.projetos.services;

import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import org.springframework.stereotype.Service;

public interface ProjetosService {

    ProjetoModel findProjetoByCpf(String cpf);

}
