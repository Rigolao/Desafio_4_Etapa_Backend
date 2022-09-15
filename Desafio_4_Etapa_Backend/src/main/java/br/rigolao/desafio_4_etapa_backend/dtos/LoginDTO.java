package br.rigolao.desafio_4_etapa_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private String senha;

}
