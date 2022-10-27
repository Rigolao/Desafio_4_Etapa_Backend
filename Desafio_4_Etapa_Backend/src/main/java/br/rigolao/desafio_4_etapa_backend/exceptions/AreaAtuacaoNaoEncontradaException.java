package br.rigolao.desafio_4_etapa_backend.exceptions;

public class AreaAtuacaoNaoEncontradaException extends RuntimeException{
    public AreaAtuacaoNaoEncontradaException() {
        super("Área de Atuação não encontrada!");
    }
}
