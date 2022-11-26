package br.rigolao.desafio_4_etapa_backend.projetos.repositories;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.ProjetoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProjetosRepository extends JpaRepository<ProjetoModel, Integer> {

    Optional<ProjetoModel> findProjetoModelByIdAndCientista(Integer id, CientistaModel cientista);

    Optional<List<ProjetoModel>> findAllByCientista(CientistaModel cientista);

    Optional<List<ProjetoModel>> findAllByPublico(Boolean publico);
}
