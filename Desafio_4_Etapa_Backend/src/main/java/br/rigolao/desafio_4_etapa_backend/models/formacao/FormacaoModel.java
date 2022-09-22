package br.rigolao.desafio_4_etapa_backend.models.formacao;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.TitulacaoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FORMACAO")
@AllArgsConstructor
@NoArgsConstructor
public class FormacaoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @EmbeddedId
    private FormacaoId id;

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
    @MapsId("idTitulacao")
    @JoinColumn(name = "id_titulacao",
            referencedColumnName = "id_titulacao")
    private TitulacaoModel titulacaoModel;

    @Getter
    @Setter
    @Column(name = "dti_formacao")
    private Date dataInicio;

    @Getter
    @Setter
    @Column(name = "dtt_formacao")
    private Date dataTermino;

}
