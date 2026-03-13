package br.com.miguel.task_manager.api.controller;

import br.com.miguel.task_manager.api.dto.UserRequestDTO;
import br.com.miguel.task_manager.api.dto.UserResponseDTO;
import br.com.miguel.task_manager.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") Long id){
        UserResponseDTO user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserRequestDTO user){
        UserResponseDTO newUser = userService.save(user);
        return ResponseEntity.status(201).body(newUser);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") Long id, @RequestBody UserRequestDTO user){
        UserResponseDTO newUser = userService.update(id, user);
        return ResponseEntity.ok().body(newUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
