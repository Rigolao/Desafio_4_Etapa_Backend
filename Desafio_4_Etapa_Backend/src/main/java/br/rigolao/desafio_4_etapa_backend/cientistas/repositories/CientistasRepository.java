package br.rigolao.desafio_4_etapa_backend.cientistas.repositories;

import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CientistasRepository extends JpaRepository<CientistaModel, Integer> {

    Optional<List<CientistaModel>> findAllByAreaAtuacaoCientista(AreaAtuacaoModel atuacaoModel);

    Optional<List<CientistaModel>> findAllByNomeStartingWithIgnoreCase(String nomeCientista);

}
