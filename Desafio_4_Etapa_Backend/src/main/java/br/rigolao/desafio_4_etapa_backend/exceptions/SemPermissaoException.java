package br.rigolao.desafio_4_etapa_backend.exceptions;

public class SemPermissaoException extends RuntimeException{

    public SemPermissaoException(String message) {
        super(message);
    }
}
