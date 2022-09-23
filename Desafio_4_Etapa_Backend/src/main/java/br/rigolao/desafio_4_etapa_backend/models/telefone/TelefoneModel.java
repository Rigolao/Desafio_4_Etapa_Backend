package br.rigolao.desafio_4_etapa_backend.models.telefone;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TELEFONE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TelefoneId telefone;

    @ManyToOne
    @MapsId("idCientista")
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

}
