package br.com.miguel.task_manager.domain.service;

import br.com.miguel.task_manager.api.dto.task.TaskRequestDTO;
import br.com.miguel.task_manager.api.dto.task.TaskResponseDTO;
import br.com.miguel.task_manager.api.dto.task.TaskUpdateDTO;
import br.com.miguel.task_manager.domain.entity.Task;
import br.com.miguel.task_manager.domain.entity.User;
import br.com.miguel.task_manager.domain.repository.TaskRepository;
import br.com.miguel.task_manager.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public List<TaskResponseDTO> findAll(Long userId){
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(TaskResponseDTO::new).toList();
    }

    public TaskResponseDTO save(Long userId, TaskRequestDTO taskDTO){
        User user = userService.getUserById(userId);
        Task task = new Task(taskDTO);
        task.setUser(user);
        task = taskRepository.save(task);
        return new TaskResponseDTO(task);
    }

    public TaskResponseDTO update(Long userId, Long taskId, TaskUpdateDTO taskDTO) {
        Task updateTask = getTaskByIdAndUserId(taskId, userId);

        if(taskDTO.getTitle() != null && !taskDTO.getTitle().isBlank()) {
            updateTask.setTitle(taskDTO.getTitle());
        }
        if(taskDTO.getDescription() != null && !taskDTO.getDescription().isBlank()) {
            updateTask.setDescription(taskDTO.getDescription());
        }
        if(taskDTO.getStatus() != null) {
            updateTask.setStatus(taskDTO.getStatus());
        }
        if(taskDTO.getPriority() != null) {
            updateTask.setPriority(taskDTO.getPriority());
        }
        if(taskDTO.getDueDate() != null) {
            updateTask.setDueDate(taskDTO.getDueDate());
        }
        if(taskDTO.getScheduleDate() != null) {
            updateTask.setScheduleDate(taskDTO.getScheduleDate());
        }

        Task task = taskRepository.save(updateTask);

        return new TaskResponseDTO(task);
    }

    public void delete(Long userId, Long taskId) {
        Task task = getTaskByIdAndUserId(taskId, userId);
        taskRepository.delete(task);
    }

    private Task getTaskByIdAndUserId(Long taskId, Long userId){
        return taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(TaskNotFoundException::new);
    }
}
