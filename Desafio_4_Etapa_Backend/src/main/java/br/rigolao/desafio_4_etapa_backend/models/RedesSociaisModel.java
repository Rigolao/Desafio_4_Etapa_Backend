package br.rigolao.desafio_4_etapa_backend.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "REDES_SOCIAIS")
public class RedesSociaisModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_rede_social")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

    @Column(name = "end_rede_social", length = 50)
    private String endereco;

    @Column(name = "tip_rede_social", length = 1)
    private String tipoRede;

    public RedesSociaisModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CientistaModel getCientista() {
        return cientista;
    }

    public void setCientista(CientistaModel cientista) {
        this.cientista = cientista;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoRede() {
        return tipoRede;
    }

    public void setTipoRede(String tipoRede) {
        this.tipoRede = tipoRede;
    }
}
