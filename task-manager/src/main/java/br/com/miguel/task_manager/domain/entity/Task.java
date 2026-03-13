package br.com.miguel.task_manager.domain.entity;

import br.com.miguel.task_manager.api.dto.TaskRequestDTO;
import br.com.miguel.task_manager.api.dto.TaskResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 60)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime finishedAt;
    private LocalDate dueDate;
    private LocalDate scheduleDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if(this.priority == null) {
            this.priority = Priority.LOW;
        }

        if(this.status == null) {
            this.status = Status.PENDING;
        }
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public Task(TaskRequestDTO taskDTO) {
        BeanUtils.copyProperties(taskDTO, this);
    }
}
