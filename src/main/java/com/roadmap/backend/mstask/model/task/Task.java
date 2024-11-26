package com.roadmap.backend.mstask.model.task;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Task {

    private Long id;
    private String tittle;
    private String description;
    private boolean active;

    public Task(Long id, String tittle, String description, boolean active) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.active = active;
    }
}
