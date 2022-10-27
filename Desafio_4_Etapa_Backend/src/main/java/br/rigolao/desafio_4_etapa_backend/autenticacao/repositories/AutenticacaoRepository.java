package br.rigolao.desafio_4_etapa_backend.autenticacao.repositories;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutenticacaoRepository extends JpaRepository<CientistaModel, String> {
    Optional<CientistaModel> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByLattes(String lattes);

    boolean existsByNome(String nome);

//    boolean existsByTelefones(List<TelefoneModel> telefones);

}
