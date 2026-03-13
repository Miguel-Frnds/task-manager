package br.com.miguel.task_manager.api.dto.task;

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
public class TaskUpdateDTO {

    @Size(max = 60)
    private String title;

    @Size(max = 100)
    private String description;

    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDate dueDate;

    @FutureOrPresent(message = "Date must be in the future or present")
    private LocalDate scheduleDate;

    private Priority priority;
    private Status status;

    public TaskUpdateDTO(Task task){
        BeanUtils.copyProperties(task, this);
    }
}
