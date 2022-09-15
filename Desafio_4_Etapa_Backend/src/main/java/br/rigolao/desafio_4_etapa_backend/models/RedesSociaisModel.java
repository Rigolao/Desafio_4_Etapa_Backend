package br.rigolao.desafio_4_etapa_backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "REDES_SOCIAIS")
@AllArgsConstructor
@NoArgsConstructor
public class RedesSociaisModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_rede_social")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_cientista",
            referencedColumnName = "id_cientista")
    private CientistaModel cientista;

    @Column(name = "end_rede_social", length = 50)
    @Getter @Setter
    private String endereco;

    @Column(name = "tip_rede_social", length = 1)
    @Getter @Setter
    private String tipoRede;

}
