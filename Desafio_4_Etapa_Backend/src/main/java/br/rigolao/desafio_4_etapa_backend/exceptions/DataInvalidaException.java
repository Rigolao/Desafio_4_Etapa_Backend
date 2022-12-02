package br.rigolao.desafio_4_etapa_backend.exceptions;

public class DataInvalidaException extends RuntimeException{
    public DataInvalidaException() {
        super("Data inserida é inválida, por favor coloque uma data válida");
    }
}
