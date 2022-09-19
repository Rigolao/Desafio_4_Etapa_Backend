package br.rigolao.desafio_4_etapa_backend.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado!");
    }
}
