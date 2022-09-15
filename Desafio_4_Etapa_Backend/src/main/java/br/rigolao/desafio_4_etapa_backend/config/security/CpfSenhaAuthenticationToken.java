package br.rigolao.desafio_4_etapa_backend.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CpfSenhaAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 570L;

    private final Object principal;

    private final Object credentials;

    public CpfSenhaAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principals,
                                       Object credentials, Boolean authenticated) {
        super(authorities);
        this.principal = principals;
        this.credentials = credentials;
        this.setAuthenticated(authenticated);
    }

    public CpfSenhaAuthenticationToken(Object principals, Object credentials, Boolean authenticated) {
        super(null);
        this.principal = principals;
        this.credentials = credentials;
        this.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
