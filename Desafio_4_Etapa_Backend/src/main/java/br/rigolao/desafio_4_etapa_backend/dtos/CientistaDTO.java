package br.rigolao.desafio_4_etapa_backend.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CientistaDTO {

    private Integer id;

    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 11)
    private String cpf;

    private Date dataNascimento;

    @NotBlank(message = "Email é obrigatório")
    private String email;

    private String emailAlternativo;

    @NotBlank(message = "Lattes é obrigatório")
    private String lattes;

    @NotBlank(message = "Senha é obrigatória")
    private String snh;

}
