package br.rigolao.desafio_4_etapa_backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TITULACAO")
@AllArgsConstructor
@NoArgsConstructor
public class TitulacaoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_titulacao")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name = "nom_titulacao", nullable = false)
    @Getter @Setter
    private String nome;
}
