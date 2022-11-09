package br.rigolao.desafio_4_etapa_backend.telefone.repositories;

import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneId;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneModel, TelefoneId> {
}
