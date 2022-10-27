package br.rigolao.desafio_4_etapa_backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Desafio4EtapaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Desafio4EtapaBackendApplication.class, args);
    }
    
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
}
