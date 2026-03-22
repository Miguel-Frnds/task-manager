package br.com.miguel.task_manager.domain.service;

import br.com.miguel.task_manager.api.dto.common.PageDTO;
import br.com.miguel.task_manager.api.dto.user.RegisterDTO;
import br.com.miguel.task_manager.api.dto.user.UserRequestDTO;
import br.com.miguel.task_manager.api.dto.user.UserResponseDTO;
import br.com.miguel.task_manager.api.dto.user.UserUpdateDTO;
import br.com.miguel.task_manager.domain.entity.User;
import br.com.miguel.task_manager.domain.repository.UserRepository;
import br.com.miguel.task_manager.exception.EmailAlreadyExistsException;
import br.com.miguel.task_manager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public PageDTO<UserResponseDTO> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        Page<UserResponseDTO> dtoPage = users.map(UserResponseDTO::new);

        return new PageDTO<>(
                dtoPage.getContent(),
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
                dtoPage.getTotalPages()
        );
    }

    public UserResponseDTO findUserById(Long id){
        User user = getUserById(id);
        return new UserResponseDTO(user);
    }

    public UserResponseDTO save(UserRequestDTO user){
        emailAlreadyExists(user.getEmail());

        User newUser = new User(user);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser = userRepository.save(newUser);
        return new UserResponseDTO(newUser);
    }

    public UserResponseDTO update(Long id, UserUpdateDTO user){
        User updateUser = getUserById(id);

        updateUser.setUpdatedAt(LocalDateTime.now());
        if(user.getUsername() != null && !user.getUsername().isBlank()) {
            updateUser.setUsername(user.getUsername());
        }

        if(user.getEmail() != null && !user.getEmail().isBlank() && !user.getEmail().equals(updateUser.getEmail())){
            emailAlreadyExists(user.getEmail());
            updateUser.setEmail(user.getEmail());
        }

        if(user.getPassword() != null && !user.getPassword().isBlank()){
            updateUser.setPassword(user.getPassword());
        }

        updateUser = userRepository.save(updateUser);
        return new UserResponseDTO(updateUser);
    }

    public void delete(Long id){
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public void register(RegisterDTO data){
        if(userRepository.existsByEmail(data.email())) {
            throw new EmailAlreadyExistsException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), data.email(), encryptedPassword, data.role());

        userRepository.save(newUser);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void emailAlreadyExists(String email){
        if(userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }
}
