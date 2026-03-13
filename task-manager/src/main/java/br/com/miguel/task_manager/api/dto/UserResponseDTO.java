package br.com.miguel.task_manager.api.dto;

import br.com.miguel.task_manager.domain.entity.Role;
import br.com.miguel.task_manager.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDTO(User user){
        BeanUtils.copyProperties(user, this);
    }
}
