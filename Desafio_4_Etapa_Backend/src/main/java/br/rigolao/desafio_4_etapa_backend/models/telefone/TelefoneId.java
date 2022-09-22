package br.rigolao.desafio_4_etapa_backend.models.telefone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column(name = "ddd_telefone", length = 2)
    private Integer ddd;

    @Getter
    @Setter
    @Column(name = "id_cientista")
    private Integer idCientista;

    @Getter
    @Setter
    @Column(name = "num_telefone", length = 10)
    private String numero;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelefoneId that = (TelefoneId) o;
        return Objects.equals(ddd, that.ddd) && Objects.equals(idCientista, that.idCientista) && Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, idCientista, numero);
    }
}
