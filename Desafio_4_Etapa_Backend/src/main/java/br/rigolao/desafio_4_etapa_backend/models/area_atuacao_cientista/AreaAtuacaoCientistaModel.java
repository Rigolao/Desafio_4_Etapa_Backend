package br.rigolao.desafio_4_etapa_backend.models.area_atuacao_cientista;

import br.rigolao.desafio_4_etapa_backend.models.AreaAtuacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "AREA_ATUACAO_CIENTISTA")
@AllArgsConstructor
@NoArgsConstructor
public class AreaAtuacaoCientistaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @Getter
    @Setter
    private AreaAtuacaoCientistaId id;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("idCientista")
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("idAreaAtuacao")
    @JoinColumn(name = "id_area_atuacao",
            referencedColumnName = "id_area_atuacao")
    private AreaAtuacaoModel areaAtuacao;

}
