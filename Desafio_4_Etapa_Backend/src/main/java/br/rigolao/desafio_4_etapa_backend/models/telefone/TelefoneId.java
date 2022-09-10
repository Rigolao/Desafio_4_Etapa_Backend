package br.rigolao.desafio_4_etapa_backend.models.telefone;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TelefoneId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "ddd_telefone", length = 2)
    private Integer ddd;

    @Column(name = "id_cientista")
    private Integer idCientista;

    @Column(name = "num_telefone", length = 10)
    private String numero;

    public TelefoneId() {

    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Integer getIdCientista() {
        return idCientista;
    }

    public void setIdCientista(Integer idCientista) {
        this.idCientista = idCientista;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

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
