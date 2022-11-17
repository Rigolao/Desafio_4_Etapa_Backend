package br.rigolao.desafio_4_etapa_backend.dtos.formacao;

import br.rigolao.desafio_4_etapa_backend.dtos.TitulacaoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormacaoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private FormacaoIdDTO id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataTermino;

    //Nome da titulação
    private String nome;

//    private TitulacaoDTO titulacao;

}
