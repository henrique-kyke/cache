package com.poc.cache.services.impl;

import com.poc.cache.mappers.TaskMapper;
import com.poc.cache.repositories.TaskRepository;
import com.poc.cache.resources.TaskWrapperResource;
import com.poc.cache.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
//    @Autowired
    private final TaskMapper mapper;

    @Override
    @Cacheable(cacheNames = "tasks", key = "#id", unless = "#result == null")
    public TaskWrapperResource find(Long id) {
        return repository.findById(id).map(task -> {
            log.info(task.toString());
            return mapper.toResource(task);
        }).orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    @Override
    @Cacheable(cacheNames = "tasks", unless = "#result==null or #result.size()==0")
    public List<TaskWrapperResource> list() {
        return mapper.toResource(repository.findAll());
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public TaskWrapperResource saveOrUpdate(TaskWrapperResource resource) {
        return mapper.toResource(repository.save(mapper.toEntity(resource)));
    }

    @Override
    @CacheEvict(key = "#id", value = "tasks")
    public void done(Long id) {
        repository.findById(id).ifPresent(task -> {
            task.setDone(true);
            task.setDueDate(Instant.now());
            repository.save(task);
        });
    }
}
