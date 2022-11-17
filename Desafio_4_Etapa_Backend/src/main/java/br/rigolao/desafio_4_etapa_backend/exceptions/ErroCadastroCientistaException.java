package br.rigolao.desafio_4_etapa_backend.exceptions;

public class ErroCadastroCientistaException extends RuntimeException{

    public ErroCadastroCientistaException() {
        super("Erro no cadastro do cientista, confirme os campos!");
    }
}
