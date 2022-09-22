package br.rigolao.desafio_4_etapa_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public final class CientistaDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 11)
    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private Date dataNascimento;

    @NotBlank(message = "Email é obrigatório")
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String emailAlternativo;

    @NotBlank(message = "Lattes é obrigatório")
    @Getter
    @Setter
    private String lattes;

    @NotBlank(message = "Senha é obrigatória")
    @Getter
    @Setter
    private String snh;

}
