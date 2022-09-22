package br.rigolao.desafio_4_etapa_backend.exceptions;

public class CientistaJaCadastradoException extends RuntimeException {

    public CientistaJaCadastradoException() {
        super("Cientista já cadastrado!");
    }

}
