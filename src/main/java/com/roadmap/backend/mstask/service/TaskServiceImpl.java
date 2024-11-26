package com.roadmap.backend.mstask.service;


import com.roadmap.backend.mstask.model.task.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl {

    private List<Task> tasks;

    public TaskServiceImpl() {

        this.tasks = new ArrayList<>();
        this.tasks.add(new Task(1L, "Task 1", "Description of Task 1", true));
        this.tasks.add(new Task(2L, "Task 2", "Description of Task 2", false));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }
    public boolean removeTask(Long id) {
        return this.tasks.removeIf(tk -> Objects.equals(tk.getId(), id));
    }

    public Task getTask(Long id) {
        return this.tasks.stream().filter(tk -> Objects.equals(tk.getId(), id)).findFirst().orElse(null);
    }

    public Task updateTask(long id, Task taskUpdate) {
        Optional<Task> optionalTask = this.tasks.stream()
                .filter(tk -> Objects.equals(tk.getId(), id)).findFirst();
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setId(taskUpdate.getId());
            task.setDescription(taskUpdate.getDescription());
            task.setTittle(taskUpdate.getTittle());
            task.setActive(taskUpdate.isActive());
            return task;
        }

        return null;
    }
}
