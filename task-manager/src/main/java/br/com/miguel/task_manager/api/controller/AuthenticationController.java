package br.com.miguel.task_manager.api.controller;

import br.com.miguel.task_manager.api.dto.user.LoginRequestDTO;
import br.com.miguel.task_manager.api.dto.user.LoginResponseDTO;
import br.com.miguel.task_manager.api.dto.user.RegisterDTO;
import br.com.miguel.task_manager.domain.entity.User;
import br.com.miguel.task_manager.domain.repository.UserRepository;
import br.com.miguel.task_manager.domain.service.UserService;
import br.com.miguel.task_manager.infra.security.TokenService;
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
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterDTO data){
        userService.register(data);
        return ResponseEntity.ok().build();
    }
}
