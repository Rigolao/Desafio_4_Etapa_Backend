package br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaAtuacaoCientistaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id_cientista")
    private Integer idCientista;

    @Column(name = "id_area_atuacao")
    private Integer idAreaAtuacao;

}
