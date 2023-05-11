package com.poc.cache.resources;

import lombok.Builder;

@Builder(toBuilder = true)
public record TaskWrapperResource(Long id, BasicTaskResource basicTaskResource) {
}
