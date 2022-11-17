package br.rigolao.desafio_4_etapa_backend.autenticacao.controllers;

import br.rigolao.desafio_4_etapa_backend.areaAtuacao.services.AreaAtuacaoService;
import br.rigolao.desafio_4_etapa_backend.areaAtuacaoCientista.services.AreaAtuacaoCientistaService;
import br.rigolao.desafio_4_etapa_backend.autenticacao.services.AutenticacaoService;
import br.rigolao.desafio_4_etapa_backend.cientistas.utils.CientistaUtil;
import br.rigolao.desafio_4_etapa_backend.config.security.CpfSenhaAuthenticationToken;
import br.rigolao.desafio_4_etapa_backend.config.security.utils.JwtTokenUtil;
import br.rigolao.desafio_4_etapa_backend.dtos.CientistaDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.LoginDTO;
import br.rigolao.desafio_4_etapa_backend.formacoes.services.FormacaoService;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.RedesSociaisModel;
import br.rigolao.desafio_4_etapa_backend.models.TitulacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.areaAtuacaoCientista.AreaAtuacaoCientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoId;
import br.rigolao.desafio_4_etapa_backend.models.formacao.FormacaoModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import br.rigolao.desafio_4_etapa_backend.redesSociais.services.RedesSociaisService;
import br.rigolao.desafio_4_etapa_backend.telefone.services.TelefoneService;
import br.rigolao.desafio_4_etapa_backend.titulacao.service.TitulacaoService;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/autorizacao")
public class AutenticacaoController extends LogUtil {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final AutenticacaoService autenticacaoService;

    private final TelefoneService telefoneService;

    private final RedesSociaisService redesSociaisService;

    private final AreaAtuacaoCientistaService areaAtuacaoCientistaService;

    private final AreaAtuacaoService areaAtuacaoService;

    private final FormacaoService formacaoService;

    private final TitulacaoService titulacaoService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                  AutenticacaoService autenticacaoService, TelefoneService telefoneService, RedesSociaisService redesSociaisService, AreaAtuacaoCientistaService areaAtuacaoCientistaService, AreaAtuacaoService areaAtuacaoService, FormacaoService formacaoService, TitulacaoService titulacaoService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.autenticacaoService = autenticacaoService;
        this.telefoneService = telefoneService;
        this.redesSociaisService = redesSociaisService;
        this.areaAtuacaoCientistaService = areaAtuacaoCientistaService;
        this.areaAtuacaoService = areaAtuacaoService;
        this.formacaoService = formacaoService;
        this.titulacaoService = titulacaoService;
    }

    @PostMapping(value = "/autenticar")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO login) {

        authenticationManager.authenticate(new CpfSenhaAuthenticationToken(login.getCpf(),
                login.getSenha(), false));

        String jwtToken = jwtTokenUtil.generateToken(new HashMap<>(), login.getCpf());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtToken);

        logInfo("Login de cientista.");

        return ResponseEntity.ok()
                .headers(headers)
                .body(jwtToken);
    }

    @PostMapping(value = "/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody @Valid CientistaDTO cientistaDTO) {
        CientistaModel cientista = CientistaUtil.preencherCientistaModel(new CientistaModel(), cientistaDTO);
        autenticacaoService.cadastrarCientista(cientista);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cientista cadastrado");
    }

}
