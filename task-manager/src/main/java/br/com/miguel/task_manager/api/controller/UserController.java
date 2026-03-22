package br.com.miguel.task_manager.api.controller;

import br.com.miguel.task_manager.api.dto.common.PageDTO;
import br.com.miguel.task_manager.api.dto.user.UserRequestDTO;
import br.com.miguel.task_manager.api.dto.user.UserResponseDTO;
import br.com.miguel.task_manager.api.dto.user.UserUpdateDTO;
import br.com.miguel.task_manager.domain.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<PageDTO<UserResponseDTO>> findAll(@PageableDefault(size = 10, sort = "username") Pageable pageable){
        return ResponseEntity.ok().body(userService.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") @NotNull @Positive Long id){
        UserResponseDTO user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO user){
        UserResponseDTO newUser = userService.save(user);
        return ResponseEntity.status(201).body(newUser);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") @NotNull  @Positive Long id, @Valid @RequestBody UserUpdateDTO user){
        UserResponseDTO newUser = userService.update(id, user);
        return ResponseEntity.ok().body(newUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Positive Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
