package br.rigolao.desafio_4_etapa_backend.models.area_atuacao_cientista;

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
public class AreaAtuacaoCientistaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id_cientista")
    @Getter
    @Setter
    private Integer idCientista;

    @Column(name = "id_area_atuacao")
    @Getter
    @Setter
    private Integer idAreaAtuacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaAtuacaoCientistaId that = (AreaAtuacaoCientistaId) o;
        return Objects.equals(idCientista, that.idCientista) && Objects.equals(idAreaAtuacao, that.idAreaAtuacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCientista, idAreaAtuacao);
    }
}
