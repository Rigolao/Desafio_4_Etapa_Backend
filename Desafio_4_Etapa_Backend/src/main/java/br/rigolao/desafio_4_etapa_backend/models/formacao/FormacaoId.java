package br.rigolao.desafio_4_etapa_backend.models.formacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormacaoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id_cientista")
    private Integer idCientista;

    @Column(name = "id_titulacao")
    private Integer idTitulacao;

}
