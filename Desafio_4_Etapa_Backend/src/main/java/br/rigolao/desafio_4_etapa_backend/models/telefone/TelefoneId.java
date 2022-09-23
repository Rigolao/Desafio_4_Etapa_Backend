package br.rigolao.desafio_4_etapa_backend.models.telefone;

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
public class TelefoneId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "ddd_telefone", length = 2)
    private Integer ddd;

    @Column(name = "id_cientista")
    private Integer idCientista;

    @Column(name = "num_telefone", length = 10)
    private String numero;

}
