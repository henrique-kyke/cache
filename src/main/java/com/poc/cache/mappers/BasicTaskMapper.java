package com.poc.cache.mappers;

import com.poc.cache.resources.BasicTaskResource;
import com.poc.cache.resources.TaskWrapperResource;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface BasicTaskMapper {

    @Mapping(source = "basicTaskResource", target = ".")
    BasicTaskResource mapToBasicTaskResource(TaskWrapperResource taskWrapperResource);

    @InheritInverseConfiguration
    TaskWrapperResource mapToTaskWrapperResource(BasicTaskMapper basicTaskMapper);

}
