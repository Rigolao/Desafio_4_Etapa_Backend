package br.rigolao.desafio_4_etapa_backend.models.formacao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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
