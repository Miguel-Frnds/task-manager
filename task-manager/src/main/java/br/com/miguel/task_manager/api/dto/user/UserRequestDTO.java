package br.com.miguel.task_manager.api.dto;

import br.com.miguel.task_manager.domain.entity.Role;
import br.com.miguel.task_manager.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 5, max = 20)
    private String password;

    @NotNull(message = "role is mandatory")
    private Role role;

    public UserRequestDTO(User user){
        BeanUtils.copyProperties(user, this);
    }
}
