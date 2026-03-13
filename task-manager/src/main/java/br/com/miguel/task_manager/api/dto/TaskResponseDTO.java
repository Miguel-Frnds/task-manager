package br.com.miguel.task_manager.api.dto;

import br.com.miguel.task_manager.domain.entity.Priority;
import br.com.miguel.task_manager.domain.entity.Status;
import br.com.miguel.task_manager.domain.entity.Task;
import br.com.miguel.task_manager.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private Long userId;
    private String username;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime finishedAt;
    private LocalDate dueDate;
    private LocalDate scheduleDate;
    private Priority priority;
    private Status status;

    public TaskResponseDTO(Task task) {
        BeanUtils.copyProperties(task, this);

        if(task.getUser() != null) {
            this.userId = task.getUser().getId();
            this.username = task.getUser().getUsername();
        }
    }
}
