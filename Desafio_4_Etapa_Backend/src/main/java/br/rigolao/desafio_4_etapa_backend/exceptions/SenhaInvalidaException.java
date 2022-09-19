package br.rigolao.desafio_4_etapa_backend.exceptions;

public class SenhaInvalidaException extends RuntimeException{
    public SenhaInvalidaException() {
        super("Senha invalida!");
    }
}
