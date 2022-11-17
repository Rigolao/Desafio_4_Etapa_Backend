package br.rigolao.desafio_4_etapa_backend.redesSociais.repositories;

import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedesSociaisRepository extends JpaRepository<RedesSociaisModel, Integer> {
}
