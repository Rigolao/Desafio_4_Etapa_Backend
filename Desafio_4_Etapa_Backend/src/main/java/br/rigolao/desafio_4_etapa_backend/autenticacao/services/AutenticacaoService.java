package br.rigolao.desafio_4_etapa_backend.autenticacao.services;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import org.springframework.stereotype.Service;

@Service
public interface AutenticacaoService  {

    CientistaModel loadUserByCpf(String cpf);

    CientistaModel saveCientista(CientistaModel cientista);
}
