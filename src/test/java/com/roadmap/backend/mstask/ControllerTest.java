package com.roadmap.backend.mstask;

import com.roadmap.backend.mstask.model.task.Task;
import com.roadmap.backend.mstask.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskServiceImpl service;

    @Test
    void test() throws Exception {
        mockMvc.perform(get("/api/tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testById() throws Exception{
        mockMvc.perform(get("/api/tareas/{id}", 1L))
                .andExpect(status().isOk())  // Espera un status 200 OK
                .andExpect(jsonPath("$").isNotEmpty())  // Asegura que el cuerpo de la respuesta no esté vacío
                .andExpect(jsonPath("$.id").value(1L))  // Verifica que el campo "id" en el JSON sea igual a 1
                .andExpect(jsonPath("$.tittle").exists())  // Verifica que el campo "title" exista
                .andExpect(jsonPath("$.description").exists())  // Verifica que el campo "description" exista
                .andExpect(jsonPath("$.active").exists());
    }

    @Test
    void testPostTareas() throws Exception {
        String taskJson = "{\"id\":1, \"tittle\":\"Tarea 1\", \"description\":\"Descripción de tarea 1\", \"active\":true}";

        mockMvc.perform(post("/api/tareas")
                        .contentType(MediaType.APPLICATION_JSON)  // Define el tipo de contenido como JSON
                        .content(taskJson))  // El cuerpo de la solicitud con el JSON
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTask() throws Exception {
        service.addTask(new Task(1L, "Tarea 1", "Descripción", true));
        String updatedTaskJson = "{\"id\":1,\"tittle\":\"Tarea Actualizada\",\"description\":\"Nueva descripción\",\"active\":false}";
        mockMvc.perform(put("/api/tareas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tittle").value("Tarea Actualizada"));
    }

    @Test
    void testDeleteTask() throws Exception {
        service.addTask(new Task(1L, "Tarea 1", "Descripción", true));
        mockMvc.perform(delete("/api/tareas/1"))
                .andExpect(status().isOk());
    }



}
