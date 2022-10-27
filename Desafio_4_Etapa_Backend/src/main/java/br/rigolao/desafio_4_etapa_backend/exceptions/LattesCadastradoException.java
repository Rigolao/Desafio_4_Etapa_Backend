package br.rigolao.desafio_4_etapa_backend.exceptions;

public class LattesCadastradoException extends RuntimeException{
    public LattesCadastradoException() {
        super("Lattes já cadastrado no sistema!");
    }
}
