package br.rigolao.desafio_4_etapa_backend.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PROJETO")
public class ProjetoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_projeto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

    @Column(name = "tit_projeto", length = 50)
    private String titulo;

    @Column(name = "res_projeto", length = 250)
    private String sobre;

    @Column(name = "dti_projeto")
    private Date dataInicio;

    @Column(name = "dtt_projeto")
    private Date dataTermino;

    @Column(name = "pub_projeto", nullable = false)
    private Boolean publico;

    public ProjetoModel() {}

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }
}
