package br.rigolao.desafio_4_etapa_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO {

    @Column(name = "ddd_telefone", length = 2)
    private Integer ddd;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id_cientista")
    private Integer idCientista;

    @Column(name = "num_telefone", length = 10)
    private String numero;

}
