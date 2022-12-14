package br.rigolao.desafio_4_etapa_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TitulacaoDTO {

    private Integer id;

    private String nome;

}
