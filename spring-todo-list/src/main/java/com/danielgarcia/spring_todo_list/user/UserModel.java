package com.danielgarcia.spring_todo_list.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @CreationTimestamp
    private LocalDate createdAt;
}
