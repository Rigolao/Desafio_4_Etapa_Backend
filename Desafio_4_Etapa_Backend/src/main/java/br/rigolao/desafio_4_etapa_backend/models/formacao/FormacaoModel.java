package br.rigolao.desafio_4_etapa_backend.models.formacao;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.TitulacaoModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FORMACAO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormacaoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FormacaoId id;

    @ManyToOne
    @MapsId("idCientista")
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

    @ManyToOne
    @MapsId("idTitulacao")
    @JoinColumn(name = "id_titulacao",
            referencedColumnName = "id_titulacao")
    private TitulacaoModel titulacaoModel;

    @Column(name = "dti_formacao")
    private Date dataInicio;

    @Column(name = "dtt_formacao")
    private Date dataTermino;

}
