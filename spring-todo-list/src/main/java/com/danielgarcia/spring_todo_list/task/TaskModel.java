package com.danielgarcia.spring_todo_list.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50)
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private UUID userId;

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("Palavra muito grande! (MAX 50)");
        }
        this.title = title;
    }

}
