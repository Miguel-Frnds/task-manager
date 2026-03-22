package br.com.miguel.task_manager.api.dto.user;

import br.com.miguel.task_manager.domain.entity.Role;

public record RegisterDTO(String username, String email, String password, Role role) {
}
