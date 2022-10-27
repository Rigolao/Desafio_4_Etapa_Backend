package br.rigolao.desafio_4_etapa_backend.models;

import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CIENTISTA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CientistaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cientista")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cientista_sequence")
    @SequenceGenerator(name = "cientista_sequence", sequenceName = "cientista_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "nom_cientista", length = 50)
    private String nome;

    @Column(name = "cpf_cientista", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "dtn_cientista")
    private Date dataNascimento;

    @Column(name = "email_cientista", length = 50, nullable = false)
    private String email;

    @Column(name = "email_alternativo_cientista", length = 50)
    private String emailAlternativo;

    @Column(name = "lattes_cientista", length = 50, nullable = false)
    private String lattes;

    @Column(name = "snh_cientista", length = 10, nullable = false)
    private String snh;

    @OneToMany(mappedBy = "cientista")
    private List<RedesSociaisModel> redesSociais;

    @OneToMany(mappedBy = "cientista")
    private List<FormacaoModel> formacoes;

    @OneToMany(mappedBy = "cientista")
    private List<TelefoneModel> telefones;

    @OneToMany(mappedBy = "cientista")
    private List<AreaAtuacaoCientistaModel> areaAtuacaoCientista;

    @OneToMany(mappedBy = "cientista")
    private List<ProjetoModel> projeto;
    
}
