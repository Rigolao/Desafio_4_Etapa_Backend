package br.rigolao.desafio_4_etapa_backend.areaAtuacaoCientista.repositories;

import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaId;
import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaAtucaoCientistaRepository extends JpaRepository<AreaAtuacaoCientistaModel, AreaAtuacaoCientistaId> {
}
