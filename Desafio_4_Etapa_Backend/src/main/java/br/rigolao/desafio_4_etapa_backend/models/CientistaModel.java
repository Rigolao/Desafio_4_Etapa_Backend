package br.rigolao.desafio_4_etapa_backend.models;

import br.rigolao.desafio_4_etapa_backend.models.area_atuacao_cientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CIENTISTA")
@AllArgsConstructor
@NoArgsConstructor
public class CientistaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cientista")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "nom_cientista", length = 50)
    @Getter
    @Setter
    private String nome;

    @Column(name = "cpf_cientista", length = 11, nullable = false, unique = true)
    @Getter
    @Setter
    private String cpf;

    @Column(name = "dtn_cientista")
    @Getter
    @Setter
    private Date dataNascimento;

    @Column(name = "email_cientista", length = 50, nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(name = "email_cientista_alternativo", length = 50)
    @Getter
    @Setter
    private String emailAlternativo;

    @Column(name = "lattes_cientista", length = 50, nullable = false)
    @Getter
    @Setter
    private String lattes;

    @Column(name = "snh_cientista", length = 10, nullable = false)
    @Getter
    @Setter
    private String snh;

    @OneToMany(mappedBy = "cientista")
    @Getter
    @Setter
    private List<RedesSociaisModel> redesSociais;

    @OneToMany(mappedBy = "cientista")
    @Getter
    @Setter
    private List<FormacaoModel> formacoes;

    @OneToMany(mappedBy = "cientista")
    @Getter
    @Setter
    private List<TelefoneModel> telefones;

    @OneToMany(mappedBy = "cientista")
    @Getter
    @Setter
    private List<AreaAtuacaoCientistaModel> areaAtuacaoCientista;

    @OneToMany(mappedBy = "cientista")
    @Getter
    @Setter
    private List<ProjetoModel> projeto;
    
}
