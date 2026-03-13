package br.com.miguel.task_manager.domain.service;

import br.com.miguel.task_manager.api.dto.user.UserRequestDTO;
import br.com.miguel.task_manager.api.dto.user.UserResponseDTO;
import br.com.miguel.task_manager.api.dto.user.UserUpdateDTO;
import br.com.miguel.task_manager.domain.entity.User;
import br.com.miguel.task_manager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDTO::new).toList();
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

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    public void emailAlreadyExists(String email){
        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
    }
}
