package br.rigolao.desafio_4_etapa_backend.models;

import br.rigolao.desafio_4_etapa_backend.models.area_atuacao_cientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CIENTISTA")
public class        CientistaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cientista")
    private Integer id;

    @Column(name = "nom_cientista", length = 50)
    private String nome;

    @Column(name = "cpf_cientista", length = 11, nullable = false, unique = true)
    private Integer cpf;

    @Column(name = "dtn_cientista")
    private Date dataNascimento;

    @Column(name = "email_cientista", length = 50, nullable = false)
    private String email;

    @Column(name = "email_cientista_alternativo", length = 50)
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

    public CientistaModel() {}

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

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public String getSnh() {
        return snh;
    }

    public void setSnh(String snh) {
        this.snh = snh;
    }

    public List<RedesSociaisModel> getRedesSociais() {
        return redesSociais;
    }

    public void setRedesSociais(List<RedesSociaisModel> redesSociais) {
        this.redesSociais = redesSociais;
    }

    public List<FormacaoModel> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<FormacaoModel> formacoes) {
        this.formacoes = formacoes;
    }

    public List<TelefoneModel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneModel> telefones) {
        this.telefones = telefones;
    }

    public List<AreaAtuacaoCientistaModel> getAreaAtuacaoCientista() {
        return areaAtuacaoCientista;
    }

    public void setAreaAtuacaoCientista(List<AreaAtuacaoCientistaModel> areaAtuacaoCientista) {
        this.areaAtuacaoCientista = areaAtuacaoCientista;
    }

    public List<ProjetoModel> getProjeto() {
        return projeto;
    }

    public void setProjeto(List<ProjetoModel> projeto) {
        this.projeto = projeto;
    }
}
