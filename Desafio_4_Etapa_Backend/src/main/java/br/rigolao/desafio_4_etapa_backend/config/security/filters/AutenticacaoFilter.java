package br.rigolao.desafio_4_etapa_backend.config.security.filters;

import br.rigolao.desafio_4_etapa_backend.config.security.CpfSenhaAuthenticationToken;
import br.rigolao.desafio_4_etapa_backend.config.security.utils.JwtTokenUtil;
import br.rigolao.desafio_4_etapa_backend.exceptions.TokenExpiradoException;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.services.AutenticacaoService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {

    private AutenticacaoService autenticacaoService;

    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AutenticacaoFilter(AutenticacaoService autenticacaoService, JwtTokenUtil jwtTokenUtil) {
        this.autenticacaoService = autenticacaoService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("Authorization");
        String cpf = null;

        if (token != null) {
            try {
                cpf = jwtTokenUtil.getSubjectFromToken(token);
                System.out.println(token);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            } catch (ExpiredJwtException e) {
                throw new TokenExpiradoException();
            }
        }

        if (cpf != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CientistaModel cientistaModel = autenticacaoService.loadUserByCpf(cpf);

            if (jwtTokenUtil.tokenIsValid(token, cientistaModel)) { //Cria um novo token quando usuário estiver autorizado
                String newToken = jwtTokenUtil.generateToken(new HashMap<>(), cientistaModel.getCpf());
                response.setHeader("Authorization", newToken);
                System.out.println(newToken);

                CpfSenhaAuthenticationToken authenticationToken = new CpfSenhaAuthenticationToken(
                        cientistaModel.getCpf(), cientistaModel.getSnh(), true);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
