package br.rigolao.desafio_4_etapa_backend.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class LoginDTO {

    @NotBlank(message = "CPF é necessário")
    private String cpf;

    @NotBlank(message = "Senha é necessária")
    private String senha;

}
