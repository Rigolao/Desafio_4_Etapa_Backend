package br.rigolao.desafio_4_etapa_backend.exceptions;

public class ProjetoNaoEncontradoException extends RuntimeException{

    public ProjetoNaoEncontradoException() {
        super("Projeto não encontrado!");
    }
}
