package br.rigolao.desafio_4_etapa_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaAtuacaoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    private String nome;

}
