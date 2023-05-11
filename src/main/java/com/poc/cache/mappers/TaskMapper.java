package com.poc.cache.mappers;

import com.poc.cache.domain.Task;
import com.poc.cache.resources.TaskWrapperResource;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MappingConfig.class, uses = {BasicTaskMapper.class})
public interface TaskMapper {
    @Mapping(source = "basicTaskResource", target = ".")
    Task toEntity(TaskWrapperResource taskWrapperResource);

    @InheritInverseConfiguration
    TaskWrapperResource toResource(Task task);

    List<TaskWrapperResource> toResource(List<Task> all);
}
