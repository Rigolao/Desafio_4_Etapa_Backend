package br.rigolao.desafio_4_etapa_backend.config.security;

import br.rigolao.desafio_4_etapa_backend.config.AuthenticationEntryPointImp;
import br.rigolao.desafio_4_etapa_backend.config.security.filters.AutenticacaoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final AuthenticationEntryPointImp authenticationEntryPointImp;

    private final AutenticacaoFilter autenticacaoFilter;

    private final AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public WebSecurityConfig(AuthenticationEntryPointImp authenticationEntryPointImp, AutenticacaoFilter autenticacaoFilter, AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationEntryPointImp = authenticationEntryPointImp;
        this.autenticacaoFilter = autenticacaoFilter;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/autorizacao/autenticar").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPointImp)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(autenticacaoFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        //retorna o getAuthenticationManager();
        return new AuthenticationManagerImp();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new AuthenticationProviderImp();
    }
}
