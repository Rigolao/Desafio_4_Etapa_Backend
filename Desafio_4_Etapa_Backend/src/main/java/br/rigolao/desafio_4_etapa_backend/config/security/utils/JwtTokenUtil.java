package br.rigolao.desafio_4_etapa_backend.config.security.utils;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_VALIDADE = 5 * 60 * 60; //TODO - Alterar tempo depois

    @Value("${jwt.secret}")
    private String secret;

    //Retorna o usuário do token
    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Retorna a data de expiração do token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //Decoda o token de acordo com o segredo
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //Retorna se o token está expirado
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Define os claims do token, como o usuário, expiração e ID
    //Assina com o algoritimo HS512 e o secret
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDADE * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    //Valida o token
    public Boolean tokenIsValid(String token, CientistaModel cientistaModel) {
        return !isTokenExpired(token) && getSubjectFromToken(token).equals(cientistaModel.getCpf());
    }
}
