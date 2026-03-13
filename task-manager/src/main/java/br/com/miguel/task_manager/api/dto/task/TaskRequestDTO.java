package br.com.miguel.task_manager.api.dto;

import br.com.miguel.task_manager.domain.entity.Priority;
import br.com.miguel.task_manager.domain.entity.Status;
import br.com.miguel.task_manager.domain.entity.Task;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskRequestDTO {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 60)
    private String title;

    @Size(max = 100)
    private String description;

    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDate dueDate;

    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDate scheduleDate;

    @NotNull(message = "Priority cannot be null")
    private Priority priority;

    @NotNull(message = "Status cannot be null")
    private Status status;

    public TaskRequestDTO(Task task) {
        BeanUtils.copyProperties(task, this);
    }
}
