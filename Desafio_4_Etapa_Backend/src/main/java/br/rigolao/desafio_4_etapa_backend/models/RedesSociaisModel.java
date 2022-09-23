package br.rigolao.desafio_4_etapa_backend.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "REDES_SOCIAIS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedesSociaisModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_rede_social")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

    @Column(name = "end_rede_social", length = 50)
    private String endereco;

    @Column(name = "tip_rede_social", length = 1)
    private String tipoRede;

}
