package br.rigolao.desafio_4_etapa_backend.dtos.formacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

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

    private String nome;

}
