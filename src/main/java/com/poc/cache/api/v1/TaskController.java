package com.poc.cache.api.v1;

import com.poc.cache.resources.TaskWrapperResource;
import com.poc.cache.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskWrapperResource>> list() {
        return ResponseEntity.ok(taskService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskWrapperResource> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.find(id));
    }

    @PostMapping
    public ResponseEntity<TaskWrapperResource> save(@RequestBody TaskWrapperResource taskWrapperResource){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveOrUpdate(taskWrapperResource));
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Void> done(@PathVariable("id") Long id){
        taskService.done(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
