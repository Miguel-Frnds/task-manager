package br.com.miguel.task_manager.domain.service;

import br.com.miguel.task_manager.api.dto.TaskRequestDTO;
import br.com.miguel.task_manager.api.dto.TaskResponseDTO;
import br.com.miguel.task_manager.domain.entity.Task;
import br.com.miguel.task_manager.domain.entity.User;
import br.com.miguel.task_manager.domain.repository.TaskRepository;
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

    public TaskResponseDTO update(Long userId, Long taskId, TaskRequestDTO taskDTO) {
        Task updateTask = getTaskById(taskId);

        if(updateTask.getUser().getId().equals(userId)) {
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

        return null;
    }

    public void delete(Long userId, Long taskId) {
        Task task = getTaskById(taskId);

        if(task.getUser().getId().equals(userId)) {
            taskRepository.delete(task);
        } else {
            throw new RuntimeException("Error");
        }
    }

    private Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found."));
    }
}
