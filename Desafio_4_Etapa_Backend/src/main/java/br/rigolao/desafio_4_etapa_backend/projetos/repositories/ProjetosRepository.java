package br.rigolao.desafio_4_etapa_backend.projetos.repositories;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjetosRepository extends JpaRepository<ProjetoModel, Integer> {

    Optional<ProjetoModel> findProjetoModelsByCientista(CientistaModel cientista);

    Optional<ProjetoModel> findAllByCientistaAndPublico(CientistaModel cientista, Boolean publico);

    Optional<List<ProjetoModel>> findAllByPublico(Boolean publico);
}
