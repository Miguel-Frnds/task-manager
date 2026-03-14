package br.com.miguel.task_manager.domain.repository;

import br.com.miguel.task_manager.domain.entity.Priority;
import br.com.miguel.task_manager.domain.entity.Status;
import br.com.miguel.task_manager.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByUserId(Long userId, Pageable pageable);
    Optional<Task> findByIdAndUserId(Long taskId, Long userId);
    Page<Task> findByUserIdAndStatus(Long userId, Status status, Pageable pageable);
    Page<Task> findByUserIdAndPriority(Long userId, Priority priority, Pageable pageable);
    Page<Task> findByUserIdAndStatusAndPriority(Long userId, Status status, Priority priority, Pageable pageable);
}
