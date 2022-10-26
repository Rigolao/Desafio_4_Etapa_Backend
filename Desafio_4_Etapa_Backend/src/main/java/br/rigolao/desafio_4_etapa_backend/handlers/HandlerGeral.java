package br.rigolao.desafio_4_etapa_backend.handlers;

import br.rigolao.desafio_4_etapa_backend.exceptions.SemPermissaoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@ControllerAdvice
public class HandlerGeral extends ResponseEntityExceptionHandler {

    private LinkedHashMap<Object, Object> _fillErrorBodyMessage(String message) {
        var body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(SemPermissaoException.class)
    public ResponseEntity<Object> handleSemPermissaoException(SemPermissaoException ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericError(Exception ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
