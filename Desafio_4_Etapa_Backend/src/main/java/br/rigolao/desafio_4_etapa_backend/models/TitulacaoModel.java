package br.rigolao.desafio_4_etapa_backend.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TITULACAO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TitulacaoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_titulacao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titulacao_sequence")
    @SequenceGenerator(name = "titulacao_sequence", sequenceName = "titulacao_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "nom_titulacao", nullable = false)
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
