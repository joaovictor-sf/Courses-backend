package com.gestao.backend.controller;

import com.gestao.backend.domain.User;
import com.gestao.backend.domain.UserRole;
import com.gestao.backend.domain.dto.AuthenticationDTO;
import com.gestao.backend.domain.dto.LoginResponseDTO;
import com.gestao.backend.domain.dto.RegisterDTO;
import com.gestao.backend.infra.security.TokenService;
import com.gestao.backend.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        //Cria um token com o login e senha
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        //Autentica o token
        var auth = this.authenticationManager.authenticate(usernamePassword);

        //Gera um token JWT
        var token = tokenService.generateToken((User) auth.getPrincipal());

        //Percorre todos os usuarios e achar a role do usuario logado
        UserRole role = null;
        for (User user : repository.findAll()) {
            if (user.getMatricula().equals(data.login())) {
                role = user.getRole();
            }
        }

        //Retorna o token
        return ResponseEntity.ok(new LoginResponseDTO(token, role));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByMatricula(data.matricula()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        //User newUser = new User(data.matricula(), encryptedPassword, data.name(), data.role(), data.courses());

        User newUser = new User(data, encryptedPassword);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
