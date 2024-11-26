package com.roadmap.backend.mstask.controller;


import com.roadmap.backend.mstask.model.task.Task;
import com.roadmap.backend.mstask.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TaskController {

    private final TaskServiceImpl service;

    public TaskController(TaskServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/tareas")
    public ResponseEntity<List<Task>> getTask() {
        return new ResponseEntity<>(service.getTasks(), HttpStatus.OK);
    }

    @GetMapping(value = "/tareas/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        Task task = service.getTask(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/tareas")
    public ResponseEntity<Boolean> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(service.addTask(task), HttpStatus.OK);
    }

    @PutMapping(value = "/tareas/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return new ResponseEntity<>(service.updateTask(id, task), HttpStatus.OK);
    }

    @DeleteMapping(value="/tareas/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.removeTask(id), HttpStatus.OK);

    }
}
