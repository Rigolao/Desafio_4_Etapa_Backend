package br.rigolao.desafio_4_etapa_backend.handlers;

import br.rigolao.desafio_4_etapa_backend.Desafio4EtapaBackendApplication;
import br.rigolao.desafio_4_etapa_backend.config.AuthenticationEntryPointImp;
import br.rigolao.desafio_4_etapa_backend.config.security.AuthenticationManagerImp;
import br.rigolao.desafio_4_etapa_backend.config.security.AuthenticationProviderImp;
import br.rigolao.desafio_4_etapa_backend.config.security.WebSecurityConfig;
import br.rigolao.desafio_4_etapa_backend.config.security.filters.AutenticacaoFilter;
import br.rigolao.desafio_4_etapa_backend.config.security.utils.JwtTokenUtil;
import br.rigolao.desafio_4_etapa_backend.exceptions.SenhaInvalidaException;
import br.rigolao.desafio_4_etapa_backend.exceptions.TokenExpiradoException;
import br.rigolao.desafio_4_etapa_backend.exceptions.UsuarioNaoEncontradoException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@ControllerAdvice(basePackageClasses = {
        Desafio4EtapaBackendApplication.class})
public class AutenticacaoHandler extends ResponseEntityExceptionHandler {

    private LinkedHashMap<Object, Object> _fillErrorBodyMessage(String message) {
        var body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException() {
        return new ResponseEntity<>(_fillErrorBodyMessage("É necessário se autenticar para acessar este serviço!"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException() {
        return new ResponseEntity<>(_fillErrorBodyMessage("Token JWT mal formatado!"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiradoException.class)
    public ResponseEntity<Object> handleTokenExpiradoException(TokenExpiradoException ex){
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<Object> handleSenhaInvalidaException(SenhaInvalidaException ex){
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(){
        return new ResponseEntity<>(_fillErrorBodyMessage("Erro ao adquirir o token JWT, tente novamente!"), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericError(Exception ex){
        return new ResponseEntity<>(_fillErrorBodyMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
