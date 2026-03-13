package br.com.miguel.task_manager.api.dto.user;

import br.com.miguel.task_manager.domain.entity.Role;
import br.com.miguel.task_manager.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {

    @Size(min = 4, max = 20)
    private String username;

    @Email
    @Size(max = 50)
    private String email;

    @Size(min = 5, max = 20)
    private String password;
    private Role role;

    public UserUpdateDTO(User user){
        BeanUtils.copyProperties(user, this);
    }
}
