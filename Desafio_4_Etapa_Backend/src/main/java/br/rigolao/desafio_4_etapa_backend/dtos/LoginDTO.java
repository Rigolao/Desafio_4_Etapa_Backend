package br.rigolao.desafio_4_etapa_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public final class LoginDTO {

    @NotBlank(message = "CPF é necessário")
    @Getter
    @Setter
    private String cpf;

    @NotBlank(message = "Senha é necessária")
    @Getter
    @Setter
    private String senha;

}
