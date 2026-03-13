package br.com.miguel.task_manager.domain.repository;

import br.com.miguel.task_manager.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
