package br.rigolao.desafio_4_etapa_backend.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerImp implements AuthenticationManager {

    @Autowired
    private AuthenticationProviderImp athenticationProviderImp;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return athenticationProviderImp.authenticate(authentication);
    }
}
