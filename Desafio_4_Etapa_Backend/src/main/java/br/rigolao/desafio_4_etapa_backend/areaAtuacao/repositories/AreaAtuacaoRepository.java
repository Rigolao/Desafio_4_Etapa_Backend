package br.rigolao.desafio_4_etapa_backend.areaAtuacao.repositories;

import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaAtuacaoRepository extends JpaRepository<AreaAtuacaoModel, Integer> {

    Optional<AreaAtuacaoModel> findByNome(String nome);

}
