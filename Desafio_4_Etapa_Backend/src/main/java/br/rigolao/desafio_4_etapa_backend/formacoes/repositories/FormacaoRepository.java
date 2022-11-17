package br.rigolao.desafio_4_etapa_backend.formacoes.repositories;

import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoId;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormacaoRepository extends JpaRepository<FormacaoModel, FormacaoId> {
}
