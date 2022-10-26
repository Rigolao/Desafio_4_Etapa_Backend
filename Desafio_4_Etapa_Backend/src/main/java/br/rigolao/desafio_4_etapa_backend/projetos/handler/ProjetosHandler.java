package br.rigolao.desafio_4_etapa_backend.projetos.handler;

import br.rigolao.desafio_4_etapa_backend.exceptions.ProjetoNaoEncontradoException;
import br.rigolao.desafio_4_etapa_backend.projetos.controllers.ProjetosController;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@ControllerAdvice(basePackageClasses = ProjetosController.class)
public class ProjetosHandler extends ResponseEntityExceptionHandler {

    private LinkedHashMap<Object, Object> _fillErrorBodyMessage(String message) {
        var body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(ProjetoNaoEncontradoException.class)
    public ResponseEntity<Object> handleProjetoNaoEncontradoException(ProjetoNaoEncontradoException ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
