package br.rigolao.desafio_4_etapa_backend.exceptions;

public class EmailCadastradoException extends RuntimeException{
    public EmailCadastradoException() {
        super("E-mail já cadastrado no sistema");
    }
}
