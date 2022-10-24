package br.rigolao.desafio_4_etapa_backend.dtos.formacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormacaoIdDTO {

    private Integer idCientista;

    private Integer idTitulacao;

}
