package br.com.miguel.task_manager.api.controller;

import br.com.miguel.task_manager.api.dto.task.TaskRequestDTO;
import br.com.miguel.task_manager.api.dto.task.TaskResponseDTO;
import br.com.miguel.task_manager.api.dto.task.TaskUpdateDTO;
import br.com.miguel.task_manager.domain.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/tasks")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskResponseDTO>> findAll(@PathVariable("userId") @NotNull @Positive Long userId) {
        List<TaskResponseDTO> tasks = taskService.findAll(userId);
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping()
    public ResponseEntity<TaskResponseDTO> save(@PathVariable("userId") @NotNull @Positive Long userId, @Valid @RequestBody TaskRequestDTO task) {
        TaskResponseDTO newTask = taskService.save(userId, task);
        return ResponseEntity.status(201).body(newTask);
    }

    @PatchMapping("{taskId}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable("userId") @NotNull @Positive Long userId,
                                       @PathVariable("taskId") @NotNull @Positive Long taskId,
                                       @Valid @RequestBody TaskUpdateDTO task) {
        TaskResponseDTO updateTask = taskService.update(userId, taskId, task);
        return ResponseEntity.ok().body(updateTask);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") @NotNull @Positive Long userId, @PathVariable("taskId") @NotNull @Positive Long taskId) {
        taskService.delete(userId, taskId);
        return ResponseEntity.noContent().build();
    }
}
