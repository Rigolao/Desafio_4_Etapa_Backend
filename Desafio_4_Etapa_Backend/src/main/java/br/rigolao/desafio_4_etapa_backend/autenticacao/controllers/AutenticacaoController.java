package br.rigolao.desafio_4_etapa_backend.autenticacao.controllers;

import br.rigolao.desafio_4_etapa_backend.autenticacao.services.AutenticacaoService;
import br.rigolao.desafio_4_etapa_backend.config.security.CpfSenhaAuthenticationToken;
import br.rigolao.desafio_4_etapa_backend.config.security.utils.JwtTokenUtil;
import br.rigolao.desafio_4_etapa_backend.dtos.CientistaDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.LoginDTO;
import br.rigolao.desafio_4_etapa_backend.dtos.TelefoneDTO;
import br.rigolao.desafio_4_etapa_backend.models.CientistaModel;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneId;
import br.rigolao.desafio_4_etapa_backend.models.telefone.TelefoneModel;
import br.rigolao.desafio_4_etapa_backend.telefone.services.TelefoneService;
import br.rigolao.desafio_4_etapa_backend.utils.LogUtil;
import br.rigolao.desafio_4_etapa_backend.utils.ObjectMapperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/autorizacao")
public class AutenticacaoController extends LogUtil {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final AutenticacaoService autenticacaoService;

    private final TelefoneService telefoneService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                  AutenticacaoService autenticacaoService, TelefoneService telefoneService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.autenticacaoService = autenticacaoService;
        this.telefoneService = telefoneService;
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
        CientistaModel cientista = new CientistaModel();
        BeanUtils.copyProperties(cientistaDTO, cientista);
        logInfo("Cientista tentando cadastro.");
        autenticacaoService.saveCientista(cientista);
        if(!cientistaDTO.getTelefones().isEmpty()){
            cientista.setTelefones(_salvarTelefones(cientistaDTO.getTelefones(), cientista));
            autenticacaoService.updateCientista(cientista);
            logInfo("Cadastro de telefone(s) do cientista: " + cientista.getNome());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Cientista cadastrado");
    }

    private List<TelefoneModel> _salvarTelefones(List<TelefoneDTO> lista, CientistaModel cientista) {
        List<TelefoneId> listaIds = ObjectMapperUtil.mapAll(lista, TelefoneId.class);
        return listaIds.stream().map(id -> {
            id.setIdCientista(cientista.getId());
            TelefoneModel telefone = new TelefoneModel(id, cientista);
            telefoneService.saveTelefone(telefone);
            return telefone;
        }).collect(Collectors.toList());
    }

}
