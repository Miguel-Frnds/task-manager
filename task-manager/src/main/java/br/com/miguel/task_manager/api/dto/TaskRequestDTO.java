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

@Getter
@Setter
@NoArgsConstructor
public class TaskRequestDTO {
    private Long userId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private LocalDate scheduleDate;
    private Priority priority;
    private Status status;

    public TaskRequestDTO(Task task) {
        BeanUtils.copyProperties(task, this);
    }
}
