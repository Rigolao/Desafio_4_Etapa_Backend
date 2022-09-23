package br.rigolao.desafio_4_etapa_backend.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "AREA_ATUACAO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaAtuacaoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_area_atuacao")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nom_area_atuacao", nullable = false)
    private String nome;

}
