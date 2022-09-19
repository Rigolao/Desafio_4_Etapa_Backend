package br.rigolao.desafio_4_etapa_backend.config.security;

import br.rigolao.desafio_4_etapa_backend.exceptions.SenhaInvalidaException;
import br.rigolao.desafio_4_etapa_backend.exceptions.UsuarioNaoEncontradoException;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.repositories.AutenticacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImp implements AuthenticationProvider {

    @Autowired
    private AutenticacaoRepository autenticacaoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CientistaModel cientistaModel = autenticacaoRepository.findByCpf(authentication.getPrincipal().toString())
                .orElseThrow(() -> new UsuarioNaoEncontradoException());

        if (!authentication.getCredentials().toString().equals(cientistaModel.getSnh())) {
            throw new SenhaInvalidaException();
        }

        return new CpfSenhaAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), true);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (CpfSenhaAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
