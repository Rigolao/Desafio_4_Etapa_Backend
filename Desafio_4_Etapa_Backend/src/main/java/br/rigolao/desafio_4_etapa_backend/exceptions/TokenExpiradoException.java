package br.rigolao.desafio_4_etapa_backend.exceptions;

public class TokenExpiradoException extends RuntimeException{
    public TokenExpiradoException() {
        super("Token JWT expirado, por favor se autentique novamente!");
    }
}
