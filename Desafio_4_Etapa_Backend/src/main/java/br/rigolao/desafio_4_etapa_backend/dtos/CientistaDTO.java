package br.rigolao.desafio_4_etapa_backend.dtos;

import br.rigolao.desafio_4_etapa_backend.dtos.formacao.FormacaoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CientistaDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;

    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @CPF
    @Size(max = 11, min = 11)
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;

    @Email
    private String emailAlternativo;

    @NotBlank(message = "Lattes é obrigatório")
    private String lattes;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Senha é obrigatória")
    private String snh;

    private List<AreaAtuacaoDTO> areaAtuacaoCientista;

    private List<RedesSociaisDTO> redesSociais;

    private List<FormacaoDTO> formacoes;

    private List<TelefoneDTO> telefones;

    private List<ProjetoDTO> projetos;

}
