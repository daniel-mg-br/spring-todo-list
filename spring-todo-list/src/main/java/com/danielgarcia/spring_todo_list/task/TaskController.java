package com.danielgarcia.spring_todo_list.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity createTask(@RequestBody TaskModel task, HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        task.setUserId((UUID) userId);

        var currentDate = LocalDate.now();
        if (
                currentDate.isAfter(ChronoLocalDate.from(task.getStartAt()))
                ||
                currentDate.isAfter(ChronoLocalDate.from(task.getEndAt()))
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Data de início / término inválida!");
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início dever ser menor que a de término!");
        }

        var taskCreated = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(taskCreated);
    }

    @GetMapping("/")
    public List<TaskModel> userTasks(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        return taskRepository.findByUserId((UUID) userId);
    }
}
