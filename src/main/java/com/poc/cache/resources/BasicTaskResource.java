package com.poc.cache.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import lombok.Builder;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record BasicTaskResource(

        @JsonProperty(required = true)
        String description,

        boolean done,

        @JsonSerialize(using = InstantSerializer.class)
        @JsonDeserialize(using = CustomInstantDeserializer.class)
        Instant dueDate
        ) {

        public static class CustomInstantDeserializer extends InstantDeserializer{

                protected CustomInstantDeserializer() {
                        super(InstantDeserializer.INSTANT, DateTimeFormatter.ISO_DATE);
                }
        }
}
