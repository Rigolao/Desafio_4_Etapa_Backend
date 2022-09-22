package br.rigolao.desafio_4_etapa_backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PROJETO")
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_projeto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

    @Column(name = "tit_projeto", length = 50)
    @Getter
    @Setter
    private String titulo;

    @Column(name = "res_projeto", length = 250)
    @Getter
    @Setter
    private String sobre;

    @Column(name = "dti_projeto")
    @Getter
    @Setter
    private Date dataInicio;

    @Column(name = "dtt_projeto")
    @Getter
    @Setter
    private Date dataTermino;

    @Column(name = "pub_projeto", nullable = false)
    @Getter
    @Setter
    private Boolean publico;

}
