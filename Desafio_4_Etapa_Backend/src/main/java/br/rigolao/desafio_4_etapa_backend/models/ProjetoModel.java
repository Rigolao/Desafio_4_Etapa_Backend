package br.rigolao.desafio_4_etapa_backend.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PROJETO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_projeto")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
