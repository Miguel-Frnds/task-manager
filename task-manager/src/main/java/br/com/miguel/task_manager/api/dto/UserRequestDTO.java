package br.com.miguel.task_manager.api.dto;

import br.com.miguel.task_manager.domain.entity.Role;
import br.com.miguel.task_manager.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private Role role;

    public UserRequestDTO(User user){
        BeanUtils.copyProperties(user, this);
    }
}
