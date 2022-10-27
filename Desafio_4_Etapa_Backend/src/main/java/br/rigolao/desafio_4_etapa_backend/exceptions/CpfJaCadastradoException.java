package br.rigolao.desafio_4_etapa_backend.exceptions;

public class CpfJaCadastradoException extends RuntimeException{
    public CpfJaCadastradoException() {
        super("CPF já foi cadastrado no sistema!");
    }
}
