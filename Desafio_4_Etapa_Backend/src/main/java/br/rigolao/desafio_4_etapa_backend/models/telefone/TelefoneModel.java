package br.rigolao.desafio_4_etapa_backend.models.telefone;

import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TELEFONE")
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

    public TelefoneModel() {
    }

    public TelefoneId getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneId telefone) {
        this.telefone = telefone;
    }

    public CientistaModel getCientista() {
        return cientista;
    }

    public void setCientista(CientistaModel cientista) {
        this.cientista = cientista;
    }
}
