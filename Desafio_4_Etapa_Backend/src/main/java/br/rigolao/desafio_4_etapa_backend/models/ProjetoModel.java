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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projeto_sequence")
    @SequenceGenerator(name = "projeto_sequence", sequenceName = "projeto_sequence", allocationSize = 1)
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

    //O hibernate ja faz a transformação para numeric(0, 1) do oracle
    @Column(name = "pub_projeto", nullable = false)
    private Boolean publico;

}
