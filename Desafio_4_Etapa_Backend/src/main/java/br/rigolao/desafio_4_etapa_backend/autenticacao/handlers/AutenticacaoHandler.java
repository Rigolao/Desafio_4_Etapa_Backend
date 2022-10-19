package br.rigolao.desafio_4_etapa_backend.autenticacao.handlers;

import br.rigolao.desafio_4_etapa_backend.exceptions.CientistaJaCadastradoException;
import br.rigolao.desafio_4_etapa_backend.exceptions.SenhaInvalidaException;
import br.rigolao.desafio_4_etapa_backend.exceptions.TokenExpiradoException;
import br.rigolao.desafio_4_etapa_backend.exceptions.UsuarioNaoEncontradoException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@ControllerAdvice
public class AutenticacaoHandler extends ResponseEntityExceptionHandler {

    private LinkedHashMap<Object, Object> _fillErrorBodyMessage(String message) {
        var body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(_fillErrorBodyMessage("É necessário se autenticar para acessar este serviço!"),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CientistaJaCadastradoException.class)
    public ResponseEntity<Object> handleCientistaJaCadastradoException(CientistaJaCadastradoException ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getFieldError().getDefaultMessage()), status);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException() {
        return new ResponseEntity<>(_fillErrorBodyMessage("Token JWT mal formatado!"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiradoException.class)
    public ResponseEntity<Object> handleTokenExpiradoException(ExpiredJwtException ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<Object> handleSenhaInvalidaException(SenhaInvalidaException ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException() {
        return new ResponseEntity<>(_fillErrorBodyMessage("Erro ao adquirir o token JWT, tente novamente!"),
                HttpStatus.EXPECTATION_FAILED);
    }

}
