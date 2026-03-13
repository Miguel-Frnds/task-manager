package br.com.miguel.task_manager.api.controller;

import br.com.miguel.task_manager.api.dto.TaskRequestDTO;
import br.com.miguel.task_manager.api.dto.TaskResponseDTO;
import br.com.miguel.task_manager.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskResponseDTO>> findAll(@PathVariable("userId") Long userId) {
        List<TaskResponseDTO> tasks = taskService.findAll(userId);
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping()
    public ResponseEntity<TaskResponseDTO> save(@PathVariable("userId") Long userId, @RequestBody TaskRequestDTO task) {
        TaskResponseDTO newTask = taskService.save(userId, task);
        return ResponseEntity.status(201).body(newTask);
    }

    @PatchMapping("{taskId}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable("userId") Long userId,
                                       @PathVariable("taskId") Long taskId,
                                       @RequestBody TaskRequestDTO task) {
        TaskResponseDTO updateTask = taskService.update(userId, taskId, task);
        return ResponseEntity.ok().body(updateTask);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        taskService.delete(userId, taskId);
        return ResponseEntity.noContent().build();
    }
}
