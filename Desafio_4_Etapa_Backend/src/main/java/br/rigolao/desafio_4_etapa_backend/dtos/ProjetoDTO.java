package br.rigolao.desafio_4_etapa_backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    private String titulo;

    private String sobre;

    private String nome;

    private String email;

    private String lattes;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataTermino;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "O projeto precisa ter sua disponibilidade")
    private Boolean publico;

}
