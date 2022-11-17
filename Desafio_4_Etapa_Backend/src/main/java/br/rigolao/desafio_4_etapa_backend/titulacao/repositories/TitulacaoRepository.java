package br.rigolao.desafio_4_etapa_backend.titulacao.repositories;

import br.rigolao.desafio_4_etapa_backend.models.TitulacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitulacaoRepository extends JpaRepository<TitulacaoModel, Integer> {

    TitulacaoModel findByNome(String nome);

}
