package br.rigolao.desafio_4_etapa_backend.models.telefone;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TELEFONE")
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @EmbeddedId
    private TelefoneId telefone;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("idCientista")
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

}
