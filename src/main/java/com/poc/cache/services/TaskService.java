package com.poc.cache.services;

import com.poc.cache.resources.TaskWrapperResource;

import java.util.List;

public interface TaskService {

    TaskWrapperResource find(Long id);
    List<TaskWrapperResource> list();
    TaskWrapperResource saveOrUpdate(TaskWrapperResource resource);

    void done(Long id);
}
