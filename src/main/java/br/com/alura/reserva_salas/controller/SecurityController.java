package br.com.alura.reserva_salas.controller;

import br.com.alura.reserva_salas.dto.securityDTO.DadosLoginDTO;
import br.com.alura.reserva_salas.dto.securityDTO.DadosTokenJWT;
import br.com.alura.reserva_salas.infra.security.TokenService;
import br.com.alura.reserva_salas.model.security.UsuarioAuth;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class SecurityController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@Valid @RequestBody DadosLoginDTO dados) {
        var Authtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());

        var authentication = manager.authenticate(Authtoken);

        var tokenJWT = tokenService.gerarToken((UsuarioAuth) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
